package spms.controls;

import java.util.Map;

import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class	
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
//		MemberDao memberDao = (MemberDao)model.get("memberDao");
		Integer no = (Integer)model.get("no");
		memberDao.delete(no);
		
		return "redirect:list.do";
	}
	
}
