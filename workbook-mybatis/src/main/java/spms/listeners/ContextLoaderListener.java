package spms.listeners;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
			applicationContext = new ApplicationContext();
			// mybatis 설정 파일(보통 자바 클래스 경로에 둠)
			String resource = "spms/dao/mybatis-config.xml";
			// getResourceAsStream() 자바 클래스 경로에 있는 파일의 입력스트림을 얻을 수 있음
			InputStream is = Resources.getResourceAsStream(resource);
			// mysql 설정 파일을 토대로 SqlSessionFactory 생성(Builder pattern)
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(is); 
			
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
			ServletContext sc = event.getServletContext();
			
			// 절대경로 호출
			System.out.println(sc.getRealPath(sc.getInitParameter("contextConfigLocation")));
			String propertiesPath = 
					sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			
			applicationContext.prepareObjectsByProperties(propertiesPath);
			applicationContext.prepareObjectsByAnnotation("spms");
			applicationContext.injectDependency();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	// 웹 애플리케이션이 종료될 때 실행
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
