package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션을 런타임시까지 사용
public @interface Component {
	String value() default ""; // 객체 이름을 저장하는 용도로 사용할 메소드
}
