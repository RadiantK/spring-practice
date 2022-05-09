package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dto.Project;
import spms.annotation.Component;

@Component("projectDao")
public class MysqlProjectDao implements ProjectDao{

	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Project> selectList() {
		String sql = "SELECT pno, pname, sta_date, end_date, state "
				+ "FROM projects ORDER BY pno DESC";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Project> projects = new ArrayList<>();
			
			while(rs.next()) {
				projects.add(new Project()
					.setNo(rs.getInt("pno"))
					.setTitle(rs.getString("pname"))
					.setStartDate(rs.getDate("sta_date"))
					.setEndDate(rs.getDate("end_date"))
					.setState(rs.getInt("state")));
			}
		
			return projects;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if( rs != null) rs.close();
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch(Exception e) {}
		}
	}

	@Override
	public int insert(Project project) {
		String sql = "INSERT INTO projects(pname, content, sta_date,"
				+ " end_date, state, cre_date, tags) "
				+ "VALUES(?, ?, ?, ?, 0, now(), ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, project.getTitle());
			pstmt.setString(2, project.getContent());
			pstmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			pstmt.setString(5, project.getTags());
			
			int result = pstmt.executeUpdate();
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch(Exception e) {}
		}
	}

	@Override
	public Project selectOne(int no) {
		String sql = "SELECT pno, pname, content, sta_date, end_date, state, "
				+ "cre_date, tags FROM projects WHERE pno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				return new Project()
					.setNo(rs.getInt("pno"))
					.setTitle(rs.getString("pname"))
					.setContent("content")
					.setStartDate(rs.getDate("sta_date"))
					.setEndDate(rs.getDate("end_date"))
					.setState(rs.getInt("state"))
					.setCreatedDate(rs.getDate("cre_date"))
					.setTags(rs.getString("tags"));
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if( rs != null) rs.close();
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch(Exception e) {}
		}
		return null;
	}

	@Override
	public int update(Project project) {
		String sql = "UPDATE projects SET pname=?, content=?, sta_date=?, "
				+ "end_date=?, state=?, tags=? WHERE pno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, project.getTitle());
			pstmt.setString(2, project.getContent());
			pstmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			pstmt.setInt(5, project.getState());
			pstmt.setString(6, project.getTags());
			pstmt.setInt(7, project.getNo());
			
			int result = pstmt.executeUpdate();
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch(Exception e) {}
		}
	}

	@Override
	public int delete(int no) {
		String sql = "DELETE FROM projects WHERE no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int result = pstmt.executeUpdate();
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch(Exception e) {}
		}
	}
}
