package com.radiantk.dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbQuery {

	private DataSource dataSource;
	
	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		String sql = "SELECT count(*) from MEMBER";
		try {
			conn = dataSource.getConnection(); // poll에서 구함
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)){
				rs.next();
				
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(conn != null) conn.close(); // 풀에 반환
			} catch(SQLException e) {}
			
		}
	}
}
