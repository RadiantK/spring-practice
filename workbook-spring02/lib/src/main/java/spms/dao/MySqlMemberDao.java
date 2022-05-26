package spms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.dto.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Member> selectList(Map<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	// 신규 회원 등록
	@Override
	public int insert(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int n = sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
			
			return n;
		}finally {
			sqlSession.close();
		}
	}
	
	// 회원 정보 하나를 가져옴
	@Override
	public Member selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int update(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Member original = sqlSession.selectOne(
					"spms.dao.MemberDao.selectOne", member.getNo());
			
			Map<String, Object> paramMap = new HashMap<>();
			if(!member.getName().equals(original.getName())) {
				paramMap.put("name", member.getName());
			}
			if(!member.getEmail().equals(original.getEmail())) {
				paramMap.put("name", member.getEmail());
			}
			
			if(paramMap.size() > 0) {
				paramMap.put("no", member.getNo());
				
				int n = sqlSession.update("spms.dao.MemberDao.update", paramMap);
				sqlSession.commit();
				
				return n;
			}
			
			return 0;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int n = sqlSession.delete("spms.dao.MemberDao.delete", no);
			sqlSession.commit();
			
			return n;
		} finally {
			sqlSession.close();
		}
	}
	
	// 로그인 후 해당하는 메일이 있으면 member객체 없으면 null 리턴
	@Override
	public Member exist(String email, String password) throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("password", password);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.MemberDao.exist", paramMap);
		} finally {
			sqlSession.close();
		}
	}
}
