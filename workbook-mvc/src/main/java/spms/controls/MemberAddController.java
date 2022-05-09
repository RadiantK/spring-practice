package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding{

	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Member member = (Member)model.get("member");
		// 입력폼 요청 할때
//		if(model.get("member") == null) {
		if(member.getEmail() == null) {
			return "/member/memberForm.jsp";
		} 
		else { // 회원 등록을 요청할 때
//			MemberDao memberDao = (MemberDao)model.get("memberDao");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}	
	}

}
