package com.radiantk.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public  MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	Member selectByEmail(String email) {
		String sql = "SELECT * FROM member WHERE email = ?";
		// jdbcTemplate.query(String sql, RowMapper<T> rowMapper, Objects... args)
		// 인덱스기반 파라미터를 가진 쿼리문(?)이면 args를 이용해서 파라미터 값 지정
		List<Member> results = jdbcTemplate.query(sql, 
			new RowMapper<Member>() {
				// ResultSet의 데이터를 읽어옴, rowNum : 현재 행의 번호
				@Override
				public Member mapRow(ResultSet rs, int rowNum) 
						throws SQLException {
					Member member = new Member(
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getTimestamp("regDate").toLocalDateTime());
					member.setId(rs.getLong("id"));
					return member;
				}
			}, email); // email이 ?에 매핑됨
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(Member member) {
	}
	
	public void update(Member member) {
		String sql = "UPDATE member SET name = ?, password = ? WHERE email = ?";
		// 쿼리의 인덱스 파라미터 값 전달
		jdbcTemplate.update(
				sql, member.getName(), member.getPassword(), member.getEmail());
	}
	
	public List<Member> selectAll(){
		String sql = "SELECT * FROM member";
		List<Member> results = jdbcTemplate.query(sql, 
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum) 
						throws SQLException {
					Member member = new Member(
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getTimestamp("regDate").toLocalDateTime());
					member.setId(rs.getLong("id"));
					return member;
				}
			
			});
		return results;
	}
	
	public int count() {
		String sql = "SELECT COUNT(*) FROM MEMBER";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
}
