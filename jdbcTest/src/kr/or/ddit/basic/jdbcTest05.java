package kr.or.ddit.basic;

import java.net.StandardSocketOptions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
    LPROD테이블에 새로운 데이터 추가하기
    
    Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고, 
    Lprod_id는 현재의 Lprod_id중에 제일 큰 값보다 1 크게 한다.
    
    입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.  
    
 */

public class jdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		String gu = null;
		int check = 0;
		String nm = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JIN", "java");

			String guPlusSql = "select max(lprod_id) from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(guPlusSql);

			while (rs.next()) {
				id = rs.getInt(1) + 1;
			}

			while (true) {
				System.out.print("Lprod_gu를 입력하세요 >> ");
				gu = scan.next();

				String checkSql = "select count(*) from lprod where lprod_gu=upper(?)";

				pstmt = conn.prepareStatement(checkSql);
				pstmt.setString(1, gu);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					check = rs.getInt(1);
				}

				if (check != 0) {
					System.out.println("해당 Lprod_gu는 존재합니다.");
				}else {
					break;
				}
			}
			System.out.print("Lprod_nm을 입력하세요 >> ");
			nm = scan.next();
			
			String sql = "insert into lprod values (?, upper(?), ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			pstmt.executeUpdate();	
			System.out.println("작업이 완료되었습니다.");
			
			String resultSql = "select * from lprod order by 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(resultSql);
			
			System.out.println("  == SQL문 처리 결과 ==");
			while (rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
