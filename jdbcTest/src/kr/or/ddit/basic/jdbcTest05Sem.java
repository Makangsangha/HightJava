package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class jdbcTest05Sem {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select max(lprod_id)+1 maxid from lprod";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int lprodId = 0;
			while (rs.next()) {
//				lprodId = rs.getInt("max(lprod_id) + 1"); // alias가 없을 떼
//				lprodId = rs.getInt(1);
				lprodId = rs.getInt("maxid"); 		// alias가 있을 떼
			}
			//----------------------------------------------
			
			// 입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			
			String lprodGu;		// 상품 분류 코드가 저장될 변수 선언
			int count = 0;		// 입력한 상품 분류 코드의 개수가 저장될 변수
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				lprodGu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count==1) {
					System.out.println("입력한 상품 분류 코드 " + lprodGu 
							+ "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
				}
			}while(count==1);
			pstmt.close();
			
			System.out.print("상품 분류명 (LPROD_NM) 입력 >> ");
			String lprodNm = scan.next();
			
			String sql3 = "insert into lprod(lprod_id, lprod_gu, lprod_nm)"
					+ " values(?,?,?) ";
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
	}

}
