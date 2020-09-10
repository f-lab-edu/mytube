package me.dev.oliver.youtubesns.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션을 사용하면 로그인이 되어있는지 유무를 확인할 수 있음.
 *
 * Documented annotation : 자바 독을 만들 때 문서화 하기 위해서 사용.
 * Retention annotaition : 어노테이션 정보를 얼마나 유지할 것인가를 나타냄. 기본값은 CLASS이고, SOURCE는 컴파일 후 삭제됨.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginValidation {
}
