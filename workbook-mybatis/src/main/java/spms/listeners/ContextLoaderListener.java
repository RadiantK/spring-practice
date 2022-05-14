package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.context.ApplicationContext;

// 웹 애플리케이션이 시작하거나 종료할 때
@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	// 웹 애플리케이션이 시작할 때 실행
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
			// 절대경로 호출
			System.out.println(sc.getRealPath(sc.getInitParameter("contextConfigLocation")));
			String propertiesPath = 
					sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			applicationContext = new ApplicationContext(propertiesPath);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	// 웹 애플리케이션이 종료될 때 실행
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
