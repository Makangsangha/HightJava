package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메소드로 
// 구성된 class 작성하기

public class DBUtil {
	// 클래스를 처음 읽을 때 무조건 처음부터 읽는 영역
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	// 객체 생성할 때마다 실행 되는 영역
	{
		
	}
	
	// Connection객체를 반환하는 메소드
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JIN", "java");
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
