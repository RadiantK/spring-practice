package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.dto.Project;

@Component("projectDao")
public class MysqlProjectDao implements ProjectDao{
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
	public List<Project> selectList() {
		// sqlSession객체 생성
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList");
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
			int n = sqlSession.update("spms.dao.ProjectDao.update", project);
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
