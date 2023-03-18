package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메소드로 
//구성된 class 작성하기 (dbinfo.properties파일의 내용으로 설정하는 방법)

// ResourceBundleTest객체 이용하기

public class DBUtil3 {
	private static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	
	// 클래스를 처음 읽을 때 무조건 처음부터 읽는 영역
	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo"); // r객체 생성
		
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
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
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JIN", "java");
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
