package com.radiantk.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public  MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Member> memberRowMapper = 
			((ResultSet rs, int rowNum) -> {
				Member member = new Member(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getTimestamp("regDate").toLocalDateTime());
				member.setId(rs.getLong("id"));
			return member;		
			});
	
	/*
	 private RowMapper<Member> memRowMapper = 
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Member member = new Member(rs.getString("email"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getTimestamp("regDate").toLocalDateTime());
					member.setId(rs.getLong("id"));
					return member;
				}
			};
	 */
	
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM member WHERE email = ?";
		// jdbcTemplate.query(String sql, RowMapper<T> rowMapper, Objects... args)
		// 인덱스기반 파라미터를 가진 쿼리문(?)이면 args를 이용해서 파라미터 값 지정
		List<Member> results = jdbcTemplate.query(
				sql, memberRowMapper, email); // email이 ?에 매핑됨
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectAll(){
		String sql = "SELECT * FROM member";
		List<Member> results = jdbcTemplate.query(sql, memberRowMapper);
		return results;
	}
	
	// 회원 가입 일자를 기준으로 검색하는 기능 구현
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		String sql = "SELECT * FROM MEMBER WHERE regdate BETWEEN ? AND ? "
				+ "ORDER BY regdate DESC";
		List<Member> results = jdbcTemplate.query(
				sql, memberRowMapper, from, to);
		return results;
	}
	
	public Member selectById(Long memId) {
		String sql = "SELECT * FROM member WHERE ID = ?";
		List<Member> results = jdbcTemplate.query(sql, memberRowMapper, memId);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(Member member) {
		String sql = "INSERT INTO member(email, password, name, regDate) "
				+ "VALUES(?,?,?,?)";
		
		//int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				// prepareStatement(String sql, String columnNames[])
				// 두 번째 파라미터는 자동생성되는 키 컬럼의 목록을 지정할 때 사용
				PreparedStatement pstmt = 
						con.prepareStatement(sql, new String[] {"id"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegDateTime()));
				return pstmt;
			}
		}, keyHolder); // keyHolder에 자동 생성된 키값 보관(primary key)
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public void update(Member member) {
		String sql = "UPDATE member SET name = ?, password = ? WHERE email = ?";
		// int update(String sql, Object... args)
		// 쿼리의 인덱스 파라미터 값 전달
		jdbcTemplate.update(
				sql, member.getName(), member.getPassword(), member.getEmail());
	}
	
	public int count() {
		String sql = "SELECT COUNT(*) FROM MEMBER";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	
}
