package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller {

	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		// 데이터 소스가 가지고있는 db접속정보를 가지는 객체를 갖는 문자열을 반환
//		MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		// db에서 리스트를 가져와서 문자열에 값을 넣어줌
		model.put("members", memberDao.selectList());
		
		// jsp파일로가서 페이지 출력
		return "/member/memberList.jsp";
	}

}
