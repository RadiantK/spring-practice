package spms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.dto.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao{
//	DataSource ds;
//	public void setDataSource(DataSource ds) {
//		this.ds = ds;
//	}
	
	// sqlSessionFactory : sqlSession을 생성하는 객체
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Project> selectList(Map<String, Object> paramMap) {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap);
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Project project) {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int n = sqlSession.insert("spms.dao.ProjectDao.insert", project);
			sqlSession.commit();
			return n;
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public Project selectOne(int no) {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Project project) {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Project original = sqlSession.selectOne(
					"spms.dao.ProjectDao.selectOne", project.getNo());
			
			Map<String, Object> paramMap = new HashMap<>();
			
			if(!project.getTitle().equals(original.getTitle())) {
				paramMap.put("title", project.getTitle());
			}
			if(!project.getContent().equals(original.getContent())) {
				paramMap.put("content", project.getContent());
			}
			if(!project.getStartDate().equals(original.getStartDate())) {
				paramMap.put("startDate", project.getStartDate());
			}
			if(!project.getEndDate().equals(original.getEndDate())) {
				paramMap.put("endDate", project.getEndDate());
			}
			if(project.getState() != original.getState()) {
				paramMap.put("state", project.getState());
			}
			if(!project.getTags().equals(original.getTags())) {
				paramMap.put("tags", project.getTags());
			}
			
			if(paramMap.size() > 0) {
				paramMap.put("no", project.getNo());
			}
			
			int n = sqlSession.update("spms.dao.ProjectDao.update", paramMap);
			sqlSession.commit();
			return n;
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int no) {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int n = sqlSession.delete("spms.dao.ProjectDao.delete", no);
			sqlSession.commit();
			return n;
		}finally {
			sqlSession.close();
		}
	}
}
