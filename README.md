# spring-practice

## Build-tool
- Maven 사용
- pom.xml(Project Object Model)파일 : 메이븐 프로젝트에 대한 설정 정보를 관리하는 파일로써 프로젝트에서 필요로 하는 의존 모듈이나 플러그인 등에 대한 설정을 담는다.
- pom.xml 설정 : spring-context모듈 의존 추가, 메이븐 컴파일러 플러그인 설정 및 자바 버전 jdk11로 설정
- 필요한 모듈에 대한 의존은 필요할 때 마다 Maven Repository에 검색해서 추가해준다.
```xml
  <!-- 의존 추가 -->
  <dependencies>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.2.12.RELEASE</version>
	</dependency>
  </dependencies>
  
  <!-- 자바 컴파일 플러그인 설정 및 자바 버전 설정  -->
  <build>
  	<plugins>
  		<plugin>
  			<artifactId>maven-compiler-plugin</artifactId>
  			<version>3.7.0</version>
  			<configuration>
  				<source>11</source>
  				<target>11</target>
  				<encoding>UTF-8</encoding>
  			</configuration>
  		</plugin>
  	</plugins>
  </build>
```
<br/>
        
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
- @Autowired(required = false) : 매칭되는 빈이 없어도 예외를 발생하지 않고 자동 주입을 수행하지 않음(자동 주입이 되는 대상이 되는 필드나 메서드에 null을 전달하지 않는다.)
<br/>
    
## 컴포넌트 스캔(Component Scan)
- @Component : class를 spring Bean으로 등록할 때 사용한다. 어노테이션에 값을 따로 지정하지않으면 클래스의 첫글자가 소문자로 바뀐 이름을 빈이름으로 사용하고(MemberDao => memberDao) 지정한 이름이 있으면 그 이름이 빈으로 등록된다.(@Component("daoMember"))
- @Component를 붙인 클래스를 스캔해서 스프링 빈으로 등록하려면 빈을 설정하는 클래스에 @ComponentScan을 적용해야 한다.
```java
@Configuration // 설정 클래스 지정
@ComponentScan(basePackages = {com.sample.spring}) // 등록된 컴포넌트(빈)를 읽어들임
public class AppConfig {}
```
- @ComponentScan(basePackages={})로 basePackages를 설정하면 지정한 패키지를 포함한 모든 하위 패키지를 스캔하게 된다.
- 컴포넌트 스캔 대상에 @Component를 제외하고도 포함되는 것들이 있는데 MVC와 관련이 있는 @Controller, @Service DB연동과 관련된 Repository및 @Aspect @Configuration이 @Component를 포함하므로 컴포넌트 스캔 대상에 포함된다.
<br/>
    
## 빈 라이프사이클
```java
// 컨테이너 초기화
ApplicationContext ctx = AnnotationConfigApplicationContext(AppContext.class);
// 컨테이너에서 빈 객체를 구해서 사용
Clazz clazz = ctx.getBean(Clazz.class);
// 컨테이너 종료
ctx.close();
```
- 스프링 컨테이너를 초기화 할때 먼저 Bean 객체를 생성하고 의존 설정을 한 뒤 빈 객체를 초기화 한다.
- 컨테이너를 종료하면 빈 객체가 소멸하게 된다.
- 빈 객체가 InitializeBean인터페이스를 구현하면 초기화 과정에서 afterPropertiesSet() 메소드를 실행하고, DisposableBean인터페이스를 구현하면 소멸과정에서 destroy()메소드를 실행한다.
- DB커넥션 풀의 초기화 과정에서 DB와 연결하고 소멸과정에서 DB연결을 끊는데 사용하거나, 채팅 클라이언트에서 초기화 과정에서 서버와 연결하고 소멸 과정에서 서버와의 연결을 끊는데에 사용된다.
- @Bean의 initMethod속성과 destroyMethod 속성을 통해서도 사용 가능하다.
```java
public class Clazz {
    void init(){} // 초기화할 때 실행
    void destroy(){} // 소멸할 때 실행
}
// @Bean의 속성값으로 컨테이너 초기화나 소멸할 때 실행될 메소드를 지정가능
@Bean(initMethod = "init", destoryMethod="destroy")
public Clazz clazz() {}
```
<br/>
		
## 빈의 생성과 스코프(범위)
- 식별자에 대해서 한 객체만 존재하는 빈은 default값으로 싱글톤(singleton)의 범위를 갖는다.
- 빈을 주입할 때마다 다른 주소를 갖는 객체를 생성 하려면 빈의 스코프를 prototype로 설정해주면 된다.
```java
@Configuration
public class AppContext{
    @Bean
    @Scope("prototype")
    public User user(){
        User user = new User();
    }
}
```
<br/>
		
### AOP(Aspect Oriented Programming) : 관점지향 프로그래밍
- AOP란 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍 기법이다. 핵심기능과 공통 기능의 구현을 분리함으로써 핵심기능을 구현한 코드의 수정 없이 공통 기능을 적용할 수 있게 만들어 준다.(프록시 기반으로 구현)
- 프록시(proxy)란 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체이며, 실제 핵심 기능을 실행하는 객체는 대상 객체라고 한다.
- 프록시는 핵심 기능을 구현하지 않고, 여러 객체에 공통으로 적용할 수 있는 기능을 구현한다.
- AOP의 주요 개념
  - Aspect : 여러 객체에 공통으로 적용되는 기능(트랜잭션 , 보안 등)
  - Advice : 언제 공통의 기능을 로직에 적용할 지를 정의(@Before @After @AfterThrowing @AfterReturning @Around)
  - JoinPoint : Advice를 적용 가능한 지점(스프링은 프록시를 이용해서 aop를 구현하기때문에 메소드 호출에 대한 JoinPoint만 지원)
  - Pointcut : JoinPoint의 부분집합으로 실제 Advice가 적용되는 JoinPoint를 나타낸다.(AspectJ문법이나 정규 표현식으로 정의가능)
  - Weaving : Advice를 핵심 로직 코드에 적용하는 것
- 메소드 시그니처(Method Signature) : 메소드의 이름과 파라미터(메소드의 리턴 타입, 예외 타입은 시그니처에 포함되지 않는다.)
```java
@Aspect // aop를 적용할 클래스에 적용하는 어노테이션
public class AopAspect {}

// spring설정 클래스에 @EnableAspectJAutoProxy  어노테이션을 달면 
// @Aspect이 붙은 빈 객체를 찾아 빈 객체의 @Pointcut설정과 Advice를 적용한다.
@Configuration
@EnableAspectJAutoProxy 
public class AppConfig {}
```
- ProceedingJoinPoint 인터페이스는 프록시 대상 객체의 메서드를 호출할 때 사용하며 다음 메소드를 제공한다.
  - Signature getSignature() : 호출되는 메소드에 대한 정보를 구한다.
  - Object getTarget() : 대상 객체를 구한다.
  - Object[] getArgs : 파라미터 목록을 구한다.



# MyBatis

## 퍼시스턴스 프레임워크(Persistence Framework)

**퍼시스턴스**

- 개발 분야에서 데이터의 지속성을 의미한다. 즉, 애플리케이션을 종료하고 다시 실행하더라도 이전에 저장한 데이터를 다시 불러올 수 있는 기술이다.

**프레임워크**

- 라이브러리가 개발에 필요한 도구들을 단순히 나열해 놓은 것이라면, 프레임워크는 동작에 필요한 구조를 어느정도 완성해 놓은 반제품 형태의 도구이다.

**퍼시스턴스 프레임워크**

- 데이터의 저장, 조회, 변경, 삭제를 다루는 클래스 및 설정 파일들의 집합으로 퍼시스턴스 프레임워크를 사용하면 JDBC의 복잡함이나 번거로움 없이 간단한 작업만으로 DB와 연동되는 시스템을 빠르게 개발할 수 있다.

퍼시스턴스 프레임워크에는 SQL문장으로 직접 DB데이터를 다루는 'SQL 맵퍼'와 자바 객체를 통해 간접적으로 DB데이터를 다루는 '객체 관계 맵퍼(Object-Relational mapper)'가 있고 SQL맵퍼의 대표로는 MyBatis, 객체 관계 맵퍼의 대표로는 하이버네이트(Hibernate),와 탑링크(TopLink)가 있다.

<br/>
<br/>
<br/>

[참조] 자바 웹 개발 워크북 (저자 엄진영)<br/>
[참조] 초보 웹 개발자를 위한 스프링5 프로그래밍 입문 (저자 최범균)
