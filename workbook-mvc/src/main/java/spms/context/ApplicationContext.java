package spms.context;

import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

//프로퍼티 파일 및 애노테이션을 이용한 객체 준비
@SuppressWarnings("deprecation")
public class ApplicationContext {
	Hashtable<String, Object> objTable = new Hashtable<>();
	
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	// 컴포넌트 어노테이션 및 properties파일을 읽어와서 경로를 얻어서 맵객체에 저장
	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		prepareObjects(props);
		prepareAnnotationObjects();
		injectDependency();
	}
	
	
	private void prepareAnnotationObjects() throws Exception{
		// 원하는 클래스를 찾아주는 도구 "spms" : 자바의 spms하위의 모든 패키지를 검색
		Reflections reflector = new Reflections("spms");
		
		// Component 어노테이션이 붙은 클래스들을 찾아줌
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		
		for(Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
	}

	private void prepareObjects(Properties props) throws Exception {
		Context ctx = new InitialContext();
		String key = null;
		String value = null;
		
		for(Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			
			if(key.startsWith("jndi.")) {
				objTable.put(key, ctx.lookup(value));
			}else {
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}
	
	private void injectDependency() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(String key : objTable.keySet()) {
			if(!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			}
		}
	}

	private void callSetter(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object dependency = null;
		for(Method m : obj.getClass().getMethods()) {
			if(m.getName().startsWith("set")) {
				// getParameterTypes : 해당 메소드를 사용하기 위한 매개변수의 타입들을 리턴
				// 메소드의 매개변수 타입과 일치하는 객체를 obj테이블에서 찾아서 리턴(MysqlMemberDao)
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null) {
					// invoke(메서드를 실행시킬 객체, 해당 메서드에 넘길 인자)
					m.invoke(obj, dependency); // 메소드 호출
				}
			}
		}
	}

	// 셋터 메소드를 호출할 때 넘겨줄 의존 객체를 찾는 일을 함
	private Object findObjectByType(Class<?> type) {
		for(Object obj : objTable.values()) {
			if(type.isInstance(obj)) { // 런타임시 타입 에러 체크
				// 매개변수 타입과 일치하는 객체를 찾았으면 그 객체의 주소 리턴
				return obj; 
			}
		}
		return null;
	}

}
