scale out을 할 때 서버가 최소 2대 이상이면 Session id를 공유 할 수 없는 문제가 발생 할 수 있다고 했습니다. 다시한번 더 말씀드리면 클라이언트가 한 대의 서버에 로그인 요청을 한 후 다시 로드 밸런서에 의해 다른 서버에 새로운 요청을 했을 때 Session id를 가지고 있지 않아 다시 로그인하라는 페이지로 리다이렉트 될 수 있습니다. 이러한 문제를 해결할 수 있는 **Sticky Session**을 알아 보도록 하겠습니다.



# Sticky Session



sticky session은 클라이언트가 요청 했을 때 한 대의 서버와 쿠키 또는 세션을 사용하여 고정된 서버를 사용하여 통신하는 방식을 말합니다.

![image](https://user-images.githubusercontent.com/55625864/95205950-096ccb80-0821-11eb-9451-59eb4c1d801f.png)



ClientA는 Load balacer로 인하여 ServerA와만 통신을 하고 ClientB도 ServerB와만 통신을 하게 하는 방식을 sticky session이라고 합니다. 

sticky session은 계속해서 session을 만들지 않고 고정된 session을 사용하기 때문에 session을 생성하는 과정이 제거되고 조금 더 빠른 성능을 가져올 수 있는 장점이 있습니다.

하지만 당연하게도 하나의 고정된 서버를 사용하면 단점이 존재합니다. 하나의 서버가 고장나게 되면 그 안에 있는 내부 session 정보들을 다 날려버릴 수도 있고 그 서버를 사용하고 있던 모든 Client는 session이 끊기는 상황이 발생됩니다.

![image-20201006222848878](C:\Users\seouz\AppData\Roaming\Typora\typora-user-images\image-20201006222848878.png)

이러한 정합성 문제점을 해결하기 위해서 이중화를 통해 session 정보를 다른 서버에도 백업해서 사용할 수 있는 session clustering 방식을 사용하게 됩니다.



# Tomcat Session Clustering

Session clustering은 영어를 해석해 보면 세션 뭉치 정도로 해석할 수 있습니다. 이를 토대로 세션을 한 뭉치로 처리해서 관리한다는 의미로 생각해 볼수 있습니다.

우선적으로 Session clustering을 알아보기 전에 서버를 어떻게 구성해야하는지 부터 알아보도록 하겠습니다. Apache tomcat을 이용해서 서버를 구성하는 방법에 대해서 알아보도록 하겠습니다.

로드밸런서를 구성할 때는 L4나 L7 스위치를 이용한 하드웨서 구성과 Apache서버를 설치해서 사용하는 소프트웨어 로드밸런서가 존재합니다. 설명에 대한 배경지식을 알기 위해서 이런 구성을 이해하고 있으면 됩니다.

![image-20201007143312544](C:\Users\seouz\AppData\Roaming\Typora\typora-user-images\image-20201007143312544.png)



Tomcat에서는 4가지의 Session Manager를 가지고 세션을 관리합니다.

- Standard Manager
- Persistent Manager
- Delta Manager
- Backup Manager

여기서는 **Delta Manager**와 **Backup Manager**에 대해서 자세히 알아보도록 하겠습니다.

**Delta Manager**는 all to all replication 방식입니다. 말그대로 모든 tomcat에 세션을 복제하여 tomcat 서버를 관리하게 됩니다. 이렇게 관리하면 정합성 문제가 해결이 되고 가용성도 좋아질 것입니다. 하지만 이 방식은 서버가 4대 이상에서는 사용하지 않는것이 좋다고 [tomcat documentation](https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html)에 쓰여있습니다.

![image](https://user-images.githubusercontent.com/55625864/95292622-c9ebc100-08ac-11eb-8c52-7fc4cf8ccef3.png)

이러한 방식으로 사용한다면 각 모든 서버에 메모리를 많이 차지 할 것이고 비효율적일 것 같습니다. 대규모 트래픽이 들어와서 엄청난 session을 관리한다면 비용 측면에서도 감당하기 힘들어 질지도 모릅니다. 그래서 간단한 서버 관리를 할 때는 좋지만 4대이상으로 서버를 관리할 때는 비추천이라고 하는것 같습니다.



**Backup Manager**는 primary-secondary session replication 방식입니다. 하나의 Main 서버가 있고 다른 tomcat 서버에 백업용 데이터를 primary 서버와 같이 가지고 있게 되어 세션을 관리하게 됩니다. 이 방식은 4대 이상의 서버를 관리할 때 사용해도 좋은 방법이니까 4대이상 서버를 관리하게 되면 이 방법을 사용해야합니다.

![image](https://user-images.githubusercontent.com/55625864/95293598-b17ca600-08ae-11eb-89ee-8bb27eb738a6.png)

tomcat에서 session 관리를 할 때는 key-value로 관리를 하게 되는데 백업용 서버에는 메인 서버와 같이 모든 session key-value를 복제합니다. key는 JSESSIONID를 value에는 임의의 Session id를 부여해서 사용한다고 합니다.

만약 Main 서버가 어떠한 이유로 서버가 다운되면 어떠한 방식으로 다른 서버로 복구를 시킬지 한번 알아 보도록 하겠습니다. 

하나의 서버가 다운되면 로드 밸런서는 통신을 하기 위해 다른 서버를 선정합니다.

#### issue1

Tomcat1의 primary 서버가 다운 되고 로드 밸런서로 인하여 Tomcat3가 선택되고 Tomcat3는 백업용 데이터를 가지고 있을 것이라고 가정합니다. 그러면 Tomcat3는 또 백업용 서버를 찾아서 Tomcat2에게 백업용 데이터를 저장하게 됩니다. 그리고 Tomcat3는 원래 Session들과의 데이터를 가지고 client와 통신을 주고 받을 수 있게 됩니다.

#### issue2

Tomcat1의 primary 서버가 다운 되고 로드 밸런서로 인하여 Tomcat2가 선택되고 Tomcat2는 백업용 데이터를 가지고 있지 않다고 가정합니다. Tomcat2에 있는 Backup Manager는 다른 서버에 백업 데이터를 가지고 있는지 물어봅니다. 그러면 Tomcat3가 백업 데이터를 가지고 있다고 Tomcat2에게 알립니다. 이로써 Tomcat3에 있는 데이터를 Tomcat2로 복제하게 되고 Tomcat3는 그대로 백업용 Tomcat으로 남아 있게 됩니다. Tomcat2는 이제 부터 Primary 서버 역할을 하게 되고 client와 통신을 주고 받을 수 있게 됩니다.



하지만 두번째 Backup Manager를 사용해서 세션을 관리할 때도 서버에 있는 데이터를 다른 서버에 복사하게 됩니다. 이 또한 단점이 될 수 있을 것입니다.  이러한 방식을 사용할 경우 서버 복구시키는데 발생하는 비용이 증가하기 때문에 Scale-Out이 많이 진행될수록 비효율적이 됩니다.



그렇다면 이 보다 더 효율적인 Session 저장소를 분리해서 사용하는 Session 저장소 분리에 대해서 알아보도록 하겠습니다.



# Session Storage 분리



#### 참고

- [What is load balancing?](https://www.citrix.com/ko-kr/glossary/load-balancing.html)
- [sticky session](https://www.imperva.com/learn/availability/sticky-session-persistence-and-cookies/)
- [Clustering/Session Replication How-To](https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html)
- [Tomcat Clustering Series Part 1 : Simple Load Balancer](https://www.ramkitech.com/2012/10/tomcat-clustering-series-simple-load.html)
- [Tomcat Clustering Series Part 2 : Session Affinity Load Balancer](https://www.javacodegeeks.com/2012/11/tomcat-clustering-series-part-2-session-affinity-load-balancer.html)
- [Tomcat Clustering Series Part 3 : Session Replication](https://www.ramkitech.com/2012/11/tomcat-clustering-series-part-3-session.html)
- [Tomcat Clustering Series Part 4 : Session Replication using Backup Manager](https://www.ramkitech.com/2012/12/tomcat-clustering-series-part-4-session.html)
- [Tomcat Clustering Series Part 5 : NginX as Load Balancer](https://www.ramkitech.com/2013/01/tomcat-clustering-series-part-5-nginx.html)
- https://www.slideshare.net/jieunsys/ss-56543446

