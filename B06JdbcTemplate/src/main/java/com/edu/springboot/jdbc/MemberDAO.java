package com.edu.springboot.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @Repository도 스프링 컨테이너 시작 시 자동으로 빈이 생성된다.
 * @Controller, @Service와 동일한 역할을 한다. 
 */
@Repository
/*
 * IMemberService 인터페이스를 구현했으므로
 * 정의된 추상메서드는 일괄적으로 오버라이딩 해야한다.
 * 컨트롤러에서 서비스 인터페이스를 통해 DAO의 각 메서드를 호출하게된다.
 * (상속이 되면 부모의 추상메서드를 통해 오버라이딩된 자식의 메서드를 호출할 수 있다.)
 */
public class MemberDAO implements IMemberService{
	/*
	 * JDBC 작업을 위해 자동주입 받는다.
	 * JDBCTemplate 빈은 개발자가 직접 설정하지 않고,
	 * build.gradle에 의존성 추가가 되어있으면 
	 * 스프링컨테이너가 자동으로 빈을 생성해준다. 
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 회원목록
	@Override
	public List<MemberDTO> select(){
		// 회원레코드를 가입일 기준으로 내림차순 정렬해 인출
		String sql = "select * from member order by regidate desc";
		
		/*
		 * query()메서드를 통해 select문 실행
		 * 쿼리문 실행 후 반환되는 ResultSet은 
		 * RowMapper가 자동으로 반복하여 저장한 후 List에 추가해서 반환해준다.
		 * 즉 레코드를 List에 저장하기 위한 반복적인 작업을 자동으로 수행해준다.
		 */
		return jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}

	// 회원등록
	@Override
	public int insert(MemberDTO memberDTO) {
		/*
		 * insert, update, delete와 같이 
		 * 행의 변화가 생기는 쿼리문은 update() 메서드를 사용한다.
		 * 실행 후 적용된 행의 개수가 int형으로 반환된다.
		 */
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			/*
			 * PreparedStatementCreator 인터페이스로 익명클래스를 생성한 후
			 * 오버라이딩 된 메서드 내에서 쿼리문을 실행하고 결과를 반환한다.
			 */
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 인파라미터가 있는 쿼리문 작성
				String sql = "insert into member (id, pass, name) values (?,?,?)";
				// 인파라미터 세팅 후 쿼리문 실행
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, memberDTO.getId());
				psmt.setString(2, memberDTO.getPass());
				psmt.setString(3, memberDTO.getName());
				
				return psmt;
			}
		});
		// 쿼리 실행 후 컨트롤러로 결과 반환
		return result;
	}

	// 회원 조회
	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {
		String sql = "select * from member where id=?";
		/*
		 * queryForObject메서드는 반드시 하나의 결과가 있어야하므로
		 * 결과가 없는 경우 예외가 발생한다.
		 * 따라서 try~catch로 감싸는 게 좋다.
		 */
		try {
			/*
			 * queryForObject() : 하나의 결과를 반환하는 select를 실행할 때 사용
			 * 인자1 : 쿼리문
			 * 인자2 : RowMapper(인출한 ResultSet을 DTO에 자동으로 입력)
			 * 인자3 : 인파라미터에 설정할 값을 배열로 선언. 순서대로 설정됨.
			 */
			memberDTO = jdbcTemplate.queryForObject(sql, 
					new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
					new Object[] {memberDTO.getId()});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return memberDTO;
	}

	// 회원 수정
	@Override
	public int update(MemberDTO memberDTO) {
		String sql = "update member set pass=?, name=? where id=?";
		/* PreparedStatementSetter 인터페이스의 익명클래스를 이용해서 인파라미터를 설정하고 쿼리문 실행 */
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1, memberDTO.getPass());
				ps.setString(2, memberDTO.getName());
				ps.setString(3, memberDTO.getId());
			}
		});
		return result;
	}

	// 회원 삭제
	@Override
	public int delete(MemberDTO memberDTO) {
		String sql = "delete from member where id=?";
		// update() 함수에서 인파라미터 설정 시 Object클래스의 배열 사용
		int result = jdbcTemplate.update(sql,
				new Object[] {memberDTO.getId()});
		return result;
	}
}
