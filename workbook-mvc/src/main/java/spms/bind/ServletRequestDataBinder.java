package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

@SuppressWarnings("deprecation")
public class ServletRequestDataBinder {
	public static Object bind(
			ServletRequest request, Class<?> dataType, String dataName) 
					throws Exception{
		if(isPrimitiveType(dataType)) {
			// 기본타입의 객체 생성
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		// request.getParameterMap() 매개변수의 이름과 값을 맵 객체에 담아서 반환
		// keySey()으로 필요한 부분인 매개변수의 이름목록만 호출
		Set<String> paramNames = request.getParameterMap().keySet();
		// 해당하는 클래스의 인스턴스를 얻음
		Object dataObject = dataType.newInstance();
		// 매개변수의 이름과 일치하는 셋터 메소드를 찾을 객체
		Method m = null;
		
		for(String paramName : paramNames) {
			// 매개변수의 이름과 일치하는 셋터 메소드를 찾음
			m = findSetter(dataType, paramName);
			if(m != null) {
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0],
						request.getParameter(paramName)));
			}
		}
		return dataObject;
	}
	
	private static boolean isPrimitiveType(Class<?> type) {
		if (type.getName().equals("int") || type == Integer.class ||
			type.getName().equals("long") || type == Long.class ||
			type.getName().equals("float") || type == Float.class ||
			type.getName().equals("double") || type == Double.class ||
			type.getName().equals("boolean") || type == Boolean.class ||
			type == Date.class || type == String.class ) {
			return true;
		}
		return false;
	}

	private static Object createValueObject(Class<?> type, String value) {
		if (type.getName().equals("int") || type == Integer.class) {
			return new Integer(value);
		} else if (type.getName().equals("float") || type == Float.class) {
			return new Float(value);
		} else if (type.getName().equals("double") || type == Double.class) {
			return new Double(value);
		} else if (type.getName().equals("long") || type == Long.class) {
			return new Long(value);
		} else if (type.getName().equals("boolean") || type == Boolean.class) {
			return new Boolean(value);
		} else if (type == Date.class) {
			return java.sql.Date.valueOf(value);
		} else {
			return value;
		}	
	}
	
	private static Method findSetter(Class<?> type, String name) {
		Method[] methods = type.getMethods();
		
		String propName = null;
		for(Method m : methods) {
			// setter메소드가 아니면 넘어감
			if(!m.getName().startsWith("set")) {
				continue;
			}
			// 파라미터값과 일치하는 세터메서드를 찾아서 리턴
			propName = m.getName().substring(3);
			if(propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}
}
