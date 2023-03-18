package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) Lprod_id값을 2개 입력 받아서 두 값 중 작은값부터 큰값 사이의 자료들을 출력하시오.

public class jdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 Lprod_id값을 입력하시오 : ");
		int input1 = scan.nextInt();

		System.out.print("두번쩨 Lprod_id값을 입력하시오 : ");
		int input2 = scan.nextInt();

		int max = Math.max(input1, input2); // 두 값 중 큰 값을 반환
		int min = Math.min(input1, input2); // 두 값 중 작은 값을 반환

		// DB작업용 객체 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JIN", "java");

//			String sql = " select * from lprod where LPROD_ID >= " + min + " and LPROD_ID <= " + max;
			String sql = " select * from lprod where LPROD_ID BETWEEN " + min + " and " + max;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("  == SQL문 처리 결과 ==");
			while (rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------------");
			}
			
			System.out.println("출력 끝...");
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (SQLException e) {}
			if (stmt != null) try {stmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}
}