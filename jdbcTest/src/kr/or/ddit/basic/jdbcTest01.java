package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC(Java DataBase Connectivity) 라이브러리를 이용한 DB자료 처리하기
public class jdbcTest01 {
	/*
	 JDBC를 이용한 DB처리 순서 
	 1. 드라이버 로딩 ==> 라이브러리를 사용할 수 있게 메모리로 읽어 들이는 작업
	 	==> JDBC API버전 4이상에서는 getConnection()메소드에서 자동으로 로딩해 주기 때문에
	 	    생략할 수 있다. (그렇지만 우리는 생략하지 않고 사용할 예정임)
	  	 Class.forName("oracle.jdbc.driver.OracleDriver");
	  	 
	 2. DB에 접속하기 ==> 접속이 완료되면 Connection객체가 반환된다.
	 	DriverManager.getConnection()메소드를 이용한다.
	 	
	 3. 질의 ==> SQL문장을 DB서버로 보내서 처리를 하고 그 결과를 얻어온다.
	  	(Statement객체 또는 PreparedStatement객체를 이용하여 작업한다.)
	 
	 4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
	    1) SQL문 select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환된다.
	    2) SQL문 select문이 아닐 경우 (insert, delete, update문 등)에는 정수값을 반환한다.
	    	(반환되는 정수값은 실행에 성공한 레코드 수를 말한다.)
	 
	 5. 사용한 자원을 반납한다. ==> close()메소드 이용
	 	
	 
	 
	 */
	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JIN", "java");
			
			// 3. 질의
			// 3-1. SQL문 작성하기
			String sql = "select * from lprod";
			
			// 3-2. Statement객체 또는 PreparedStatement 객체 생성
			stmt = conn.createStatement();
			
			// 3-3. SQL문을 서버로 보내서 결과를 얻어온다.
			//		(실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리
			// 한 레코드씩 화면에 출력하기
			System.out.println("  == SQL문 처리 결과 ==");
			
			// ResultSet객체에 저장된 데이터를 꺼내오려면 반복문과 next()메소드를 이용한다.
			
			// rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드 위치로
			//				 이동 시키고 그 곳에 데이터가 있으면 true, 없으면 false를 반환한다.
			while(rs.next()) {
				// ResultSet객체의 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식1) rs.get자료형이름("컬럼명 또는 alias명")
				// 형식2) rs.get자료형이름(컬럼번호)	==> 컬럼번호는 1부터 시작
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------------");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 5. 자원 반납
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		
	}
}
