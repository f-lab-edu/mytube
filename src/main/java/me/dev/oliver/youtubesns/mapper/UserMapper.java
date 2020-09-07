package me.dev.oliver.youtubesns.mapper;

import me.dev.oliver.youtubesns.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @SpringBootApplication : @ComponentScan 이라는 어노테이션 프로세서를 갖고 있어서 @Component가 있는 클래스를 찾아서 모두
 * Bean(빈)으로 등록.
 * @Component : @Controller(Presentation), @Service(Service), @Repository(Persistance/DAO) 포함.
 */

/**
 * @Mapper 아래 3가지 설명을 확인할 것.
 * - spring boot가 이 인터페이스를 Mapper로 인식함.
 * - 해당 interface를 마이바티스 매퍼로 등록하겠다는 뜻.
 * - 패키지를 포함하는 클래스명 부분이 mapper xml 상의 namespace가 선택되고 인터페이스 메소드가 query id로 호출되는 방식을 사용 가능.
 * <p>
 * int값을 반환함으로써 SQL문의 레코드 개수를 확인하고, 이로써 동작 유무 확인 가능.
 */
@Repository
@Mapper
public interface UserMapper {

  int insertUser(UserDto user);

  UserDto findByInfos(UserDto user);

  int updatePassword(UserDto user);

  int updateUserAddr(UserDto user);

  int deleteUser(String userId);

  /** Id와 패스워드가 맞으면 true 아니면 false */
  boolean findByIdAndPw(UserDto user);

  boolean isExistsId(String userId);

}
