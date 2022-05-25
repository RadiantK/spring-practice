package exam.test14;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("exam/test14/beans.xml");
		
		System.out.println("기본 : 싱글톤 방식");
		Engine e1 = (Engine) ctx.getBean("hyundaiEngine");
		Engine e2 = (Engine) ctx.getBean("hyundaiEngine");
		
		System.out.println(e1 == e2);
		
		System.out.println("프로토타입 방식");
		
		Engine e3 = (Engine) ctx.getBean("kiaEngine");
		Engine e4 = (Engine) ctx.getBean("kiaEngine");
		
		System.out.println(e3 == e4);
		
		ctx.close();
	}
}
