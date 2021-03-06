package spms.controls;

import java.util.Map;

import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dto.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class,
			"member", spms.dto.Member.class	
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Member member = (Member)model.get("member");

		if(member.getEmail() == null) {	
			Integer no = (Integer)model.get("no");
			Member memberInfo = memberDao.selectOne(no);
			model.put("member", memberInfo);
			
			return "/member/memberUpdateForm.jsp";
		}
		// Member member = (Member)model.get("member");
		memberDao.update(member);
		return "redirect:list.do";
	}
	
}
