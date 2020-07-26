package me.dev.oliver.youtubesns.mapper;

import java.util.List;
import me.dev.oliver.youtubesns.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// @SpringBootApplication 어노테이션은 @ComponentScan 이라는 어노테이션
// 프로세서를 갖고 있어서 @Component가 있는 클래스를 찾아서 모두 Bean(빈)으로 등록.
// @Component 어노테이션은 @Controller, @Service, @Repository 포함.
@Repository
// spring boot가 이 인터페이스를 Mapper로 인식함.
@Mapper
public interface UserMapper {
  // id 회원 조회
  UserDto getUser(@Param("id") String id);
  // 회원 전체 조회
  List<UserDto> getUserList();

  // int값을 반환함으로써 SQL문의 레코드 개수를 확인하고, 이로써 동작 유무 확인 가능
 // 회원 추가
  int insertUser(UserDto user);
  // 회원 비밀번호 변경
  int changePassword(@Param("id") Integer id, @Param("pw") String pw);
  // 회원 삭제
  int deleteUser(@Param("id") Integer id);
}
