package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dto.Member;

@Component("/auth/login.do")
public class LogInController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"loginInfo", spms.dto.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Member loginInfo = (Member)model.get("loginInfo");
		
		if(loginInfo.getEmail() == null ) { // 입력폼 요청
			return "/auth/loginForm.jsp";
		}
		// 회원 등록 요청
		Member member = memberDao.exist( 
				loginInfo.getEmail(), 
				loginInfo.getPassword());
		
		if(member != null) {
			HttpSession session = (HttpSession)model.get("session");
			session.setAttribute("member", member);
			return "redirect:../member/list.do";
		} else {
			return "/auth/loginFail.jsp";
		}
		
	}
}
