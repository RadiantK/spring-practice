package spms.controls;

import java.util.Map;

import dto.Project;
import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding{

	ProjectDao projectDao;
	
	public ProjectAddController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", dto.Project.class	
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		
		if(project.getTitle() == null) {
			return "/project/projectForm.jsp";
		}
		
		projectDao.insert(project);
		return "redirect:list.do";
	}
	
}
