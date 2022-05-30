package spms.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 웹 애플리케이션이 시작하거나 종료할 때
@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	static ClassPathXmlApplicationContext applicationContext;
	
	public static ClassPathXmlApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	// 웹 애플리케이션이 시작할 때 실행
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			applicationContext = new ClassPathXmlApplicationContext("beans.xml");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	// 웹 애플리케이션이 종료될 때 실행
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
