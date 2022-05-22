package spms.dao;

import java.util.List;
import java.util.Map;

import spms.dto.Project;

public interface ProjectDao {
	List<Project> selectList(Map<String, Object> paramMap);
	int insert(Project project);
	Project selectOne(int no);
	int update(Project project);
	int delete(int no);
}
