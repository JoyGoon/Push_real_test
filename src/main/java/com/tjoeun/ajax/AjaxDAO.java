package com.tjoeun.ajax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AjaxDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public AjaxDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "tjoeunit", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스가 없거나 읽어올 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 정보가 올바르지 않습니다.");
		}
	}

	public ArrayList<AjaxVO> search(String name) {
		System.out.println("AjaxDAO의 search()클래스 발동");
		
		ArrayList<AjaxVO> list = null;
		
		try {
			String sql = "select * from ajax where name like ? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  '%' + name + '%');
			rs = pstmt.executeQuery();
		
			list = new ArrayList<>();
			while (rs.next()) {
				
				AjaxVO vo = new AjaxVO();
				
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				
				list.add(vo);
				
				
			}
			
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int insert(AjaxVO vo) {
		System.out.println("AjaxDAO 클래스 insert");
		System.out.println(vo);
		
		
		try {
			String sql = "insert into ajax (idx, name, age, gender, email) "
					+ "values (ajax_idx_seq.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getEmail());
			
			// sql 정상 실행된 갯수(1) 리턴
			return pstmt.executeUpdate();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		} 
		
		return -1;
		
	}
	
	
	

}
