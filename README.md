# youtube-sns

동영상 공유 SNS

유튜브를 밴치마킹하여 개발하는 youtube-sns 플랫폼을 개발합니다. 서버 위주의 프로젝트이며 프론트단은 제외합니다. UI 구현은 이해를 위해 카카오 오븐 프로토타입을 사용했습니다. 또한, 자세한 구현 내용을 PR에서 확인할 수 있습니다.



### 프로젝트 컨벤션(Convention)

- Pull request를 통하여 merge합니다.
- 코드리뷰를 통과해야만 병합gkqslek.
- wiki에 프로젝트 소개와 기술적 문제 해결 내용을 정리합니다.
- 지속적인 성능 개선 및 코드 리팩토링을 지향합니다.
- 코드 컨벤션
  - [Google code Style](https://google.github.io/styleguide/javaguide.html)을 준수
    - CheckStyle-IDEA 플러그인 적용 
      - [인텔리제이 code Style 설정](https://jiyeonseo.github.io/2016/11/15/setting-java-google-style-to-intellij/)
      - [intellij-java-google-style.xml 다운로드](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse)



### 브랜치 관리 전략(Git flow)

![image](https://user-images.githubusercontent.com/55625864/85289648-bb7ec280-b4d2-11ea-9141-ecde76643709.png)

![image](https://user-images.githubusercontent.com/55625864/85288567-e8ca7100-b4d0-11ea-9093-9d721a88fd0f.png)

- master : 베포 단계에 출시될 수 있는 브랜치입니다.
- develop : 개발이 끝난 버전을 개발하는 브랜치입니다.
- feature : 기능을 개발하는 브랜치입니다.
- release : 베포 버전을 준비하는 브랜치입니다.
- hotfix : 베포 버전에서 발생한 버그를 수정 하는 브랜치입니다.



- 브랜치 참고 문헌
  - [Bitbucket](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
  - [우아한형제들 기술블로그](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)



### 깃 커밋 메시지 스타일 가이드

자신만의 커밋 메시지 작성 원칙을 정하고 일관성있게 커밋합니다. 많은 작성 가이드 중 유다시티의 커밋 메시지 작성 가이드를 사용한다.

```
Type: 제목(Title)

본문(Body)

꼬리말(Footer)
```

#### 커밋 타입 - Commit Type

- feat: 새로운 기능을 추가
- fix: 버그 수정
- docs: 문서 수정
- style: 코드 포맷 변경, 세미 콜론 누락, 코드 수정 없음
- refactor: 코드 리팩터링
- test: 테스트 코드, 테스트 리펙토링
- chore: 빌드 업무 업데이트, 패키지 매니저 설정

#### 제목 - Title

- 제목은 50자를 넘기지 않고, 첫글자는 대문자, 마침표 금지
- 과거시제가 아닌 명령어로 동사를 먼저 작성
  - "Fixed" --> "Fix"
  - "Modified" --> "Modify"

#### 본문 (선택사항) -  Body

- 본문은 커밋의 상세 내용을 작성
- 제목과 본문 사이에 한 줄 띄움
- 본문은 한 줄에 72자 이내로 작성
- 한 줄을 띄워 문단으로 나누거나 ·(bullet)을 사용해 내용을 구분한다.



#### 꼬리말 (선택사항) - Footer

꼬리말에는 issue tracker id를 추가한다.

```
Resolves: #123
See also: #456, #789
```



#### 커밋 수정 및 삭제

-  [커밋 히스토리를 수정](https://www.atlassian.com/git/tutorials/rewriting-history) 
- [히스토리 전체 삭제](https://gist.github.com/heiswayi/350e2afda8cece810c0f6116dadbe651)



- 커밋 메시지 스타일 가이드 참고 문헌
  - [Udacity](https://udacity.github.io/git-styleguide/)

