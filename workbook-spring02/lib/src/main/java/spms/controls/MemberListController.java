package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"orderCond", String.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderCond", model.get("orderCond"));
		
		// db에서 리스트를 가져와서 문자열에 값을 넣어줌
		model.put("members", memberDao.selectList(paramMap));
		
		// jsp파일로가서 페이지 출력
		return "/member/memberList.jsp";
	}

}
