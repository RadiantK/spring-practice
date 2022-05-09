package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.dto.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	
//	// db 접속 정보를 다루는 객체
//	Connection con = null;
//	// db에 sql문(쿼리)을 보낼 수 있게 해주는 객체
//	Statement stmt = null;
//	// 서버에서 쿼리 결과를 가져올 수 있음
//	ResultSet rs = null;
	
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Member> selectList() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			// db에 sql문을 보내는 객체 반환
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, MNAME, EMAIL, CRE_DATE"
					+ " from MEMBERS"+" order by MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
			}			
			return members;
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {}
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {}
//			if(connection != null) {
//				conPool.returnConnection(connection);
//			}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}
	}
	
	// 신규 회원 등록
	@Override
	public int insert(Member member) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			// now() sql문을 실행하는 시점의 날짜 시간 반환
			stmt = connection.prepareStatement(
					"insert into MEMBERS(EMAIL, PWD, MNAME, CRE_DATE,MOD_DATE)"
					+ "VALUES(?,?,?, NOW(), NOW())");
			// sql문에서 ?에 들어갈 내용 (매개변수 번호는 배열과달리 1부터 시작)
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(stmt != null ) {
					stmt.close();
				}
			} catch (Exception e) {}	
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}	
	}
	
	@Override
	public int delete(int no) throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			
			stmt.executeUpdate("delete from MEMBERS where MNO="+ no);
			return no;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(stmt !=null) {
					stmt.close();
				}
			} catch (Exception e) {}
//			if(connection != null) {
//				conPool.returnConnection(connection);
//			}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}
	}
	
	// 회원 정보 하나를 가져옴
	@Override
	public Member selectOne(int no) throws Exception {
		
		Connection connection =null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery("select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS"
					+ " where MNO=" + no);
			
			if(rs.next()) {
				Member member = new Member()
						.setNo(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"))
						.setCreatedDate(rs.getDate("CRE_DATE"));
				return member;
			} else {
				throw new Exception("해당하는 번호의 회원을 찾을 수 없습니다.");
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(rs != null ) {
					rs.close();
				} 
			} catch (Exception e) {}
			try {
				if(stmt != null ) {
					stmt.close();
				} 
			} catch (Exception e) {}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}	
	}
	
	@Override
	public int update(Member member) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"update MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" + " where MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}
	}
	
	// 로그인 후 해당하는 메일이 있으면 member객체 없으면 null 리턴
	@Override
	public Member exist(String email, String password) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
//			connection = conPool.getConnection();
			connection = ds.getConnection();
			
			stmt = connection.prepareStatement("select MNAME, EMAIL from MEMBERS"
					+ " where EMAIL=? and PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				Member member = new Member()
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"));
				return member;
			} else {
				return null;
			}	
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				 if(rs != null) {
					 rs.close();
				 }
			} catch(Exception e) {}
			try {
				 if(stmt != null) {
					 stmt.close();
				 }
			} catch(Exception e) {}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {}
		}
	}
}
