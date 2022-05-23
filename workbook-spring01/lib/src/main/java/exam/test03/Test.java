package exam.test03;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("exam/test03/beans.xml");
		
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		for(String alias : ctx.getAliases("exam.test03.Score#0")) {
			System.out.println(alias);
		}
		
		Score score1 = (Score) ctx.getBean("exam.test03.Score");
		Score score2 = (Score) ctx.getBean("exam.test03.Score#0");
		if(score1 == score2) System.out.println("score == score#0");
		
		Score score3 = (Score) ctx.getBean("exam.test03.Score#1");
		if(score1 != score3) System.out.println("score1 != score#1");
		
		// 인스턴스가 여러개 있으므로 예외
		// Score score4 = (Score) ctx.getBean(exam.test03.Score.class);
		ctx.close();
	}
}
