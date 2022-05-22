package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding{
	ProjectDao projectDao;
	
	public ProjectListController setMemberDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
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
		
		model.put("projects", projectDao.selectList(paramMap));
		
		return "/project/projectList.jsp";
	}

	
}
