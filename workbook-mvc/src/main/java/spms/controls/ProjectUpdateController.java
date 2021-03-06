package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.dto.Project;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public ProjectUpdateController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class,
			"project", spms.dto.Project.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		
		if(project.getTitle() == null) { // 상세정보 요청
			Integer no = (Integer)model.get("no");
			Project detailInfo = projectDao.selectOne(no);
			model.put("project", detailInfo);
			return "/project/projectUpdateForm.jsp";
		}
		// 프로젝트 변경
		projectDao.update(project);
		return "redirect:list.do";
	}

}
