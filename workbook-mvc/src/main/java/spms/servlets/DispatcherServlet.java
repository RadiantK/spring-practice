package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.listeners.ContextLoaderListener;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	// 프론트 컨트롤러는 클라이언트 요청을 처리해야하기때문에 서블릿이어야함 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 서블릿을 사용해 클라이언트에게 전송할 데이터종류
		response.setContentType("text/html; charset=UTF-8");
		// 서블릿 경로 getServletPath() /member/list.do
		String servletPath = request.getServletPath();
		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			// 페이지 컨트롤러에게 전달할 Map 객체를 준비
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			// 서블릿 URL을 찾음
			Controller pageController = (Controller)ctx.getBean(servletPath);
			if (pageController == null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			}
			
			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			// 페이지 컨트롤러가 알려준 JSP로 실행을 위임
			// execute의 반환값은 화면을 출력하는 JSP의 URL
			String viewUrl = pageController.execute(model);
			
			// model이라는 맵객체의 키값만큼 반복문 실행
			for(String key : model.keySet()) {
				// key에 대한 value값을 key라는 변수에 할당 (.jsp페이지)
				request.setAttribute(key, model.get(key));
			}
			// 단, 뷰URL이 redirect로 시작하면 인클루딩 말고 sendRedirect 수행
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
			rd.include(request, response);
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void prepareRequestData(HttpServletRequest request,
			HashMap<String, Object> model, DataBinding dataBinding) throws Exception{
		
		// 페이지 컨트롤러에 필요한 데이터가 무엇인지 확인 Object[] arr = [데이터이름, 데이터타입]
		Object[] dataBinders = dataBinding.getDataBinders();
		// 데이터 이름을 보관할 임시 변수
		String dataName = null;
		// 데이터 타입을 보관할 임시 변수
		Class<?> dataType = null;
		// 데이터 객체를 보관할 임시 변수
		Object dataObj = null;
		// 객체에 [데이터이름, 데이터타입, 데이터이름, 데이터타입]식으로 들어있으니 +2로해서 반복문 실행
		for(int i = 0; i < dataBinders.length; i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i+1];
			// 데이터 이름과 일치하는 요청 매개변수를 찾고 데이터 타입을 통해 해당클래스의 인스턴스 생성
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}
