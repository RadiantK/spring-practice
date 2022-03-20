# spring-practice

## DI(Dependency Injection) : 의존성 주입
- 의존이란 변경에 의해 영향을 받는 관계를 의미
- DI란 의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식
- @Configuration : 스프링 설정 클래스로 지정한다는 의미
- @Bean : 스프링 Bean으로 등록(개발자가 아닌 스프링 컨테이너에서 관리)
- @Configuration으로 선언한 스프링 설정을 담는 클래스에서 등록한 스프링Bean을 사용하려면 설정 클래스를 이용해서 스프링 컨테이너인 ApplicationContext객체를 선언해서 사용해야 한다. 
```java
ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
```
- 의존성 자동주입 @Autowired : 의존에 필요한 Bean을 찾아서 의존성을 자동 주입함(동일한 빈이 두 개 이상이면 예외 발생)
- @Qualifier : 동일한 빈이 존재할 때 의존 객체를 선택할 수 있음(자동 주입할 Bean을 지정)
- @Autowired(required = false) : 매칭되는 빈이 없어도 예외를 발생하지 않고 자동 주입을 수행하지 않음



<br/>
<br/>
<br/>
[출처] 초보 웹 개발자를 위한 스프링5 프로그래밍 입문
