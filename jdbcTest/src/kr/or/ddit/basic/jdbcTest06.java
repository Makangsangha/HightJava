package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	회원을 관리하는 프로그램을 작성하시오.
 	( MYMEMBER 테이블 이용 )
 	
 	아래의 메뉴를 모두 구현하시오.(CRUD기능 구현) 
 	CRUD = Create, Read, Update, Delete
 	
 	메뉴 예시)
 	1. 자료 추가			--> insert(C)
 	2. 자료 삭제			--> delete(D)
 	3. 자료 수정			--> update(U)
 	4. 전체 자료 출력		--> select(R)
 	0 작업 끝.
 	
 	조건)
 	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
 	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
*/

public class jdbcTest06 {
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		jdbcTest06 test = new jdbcTest06();
		test.start();
	}

	public jdbcTest06() {
		conn = DBUtil.getConnection();
	}

	private void start() {
		boolean screen = true;
		System.out.println("  MyMember 테이블 CRUD");
		while (screen) {
			String choiceNum = startMenu();
			switch (choiceNum) {
			case "1":
				dataInsert();
				break;
			case "2":
				dataDelete();
				break;
			case "3":
				dataUpdate();
				break;
			case "4":
				dataFullSelect();
				break;
			case "0":
				System.out.println("작업을 종료합니다.");
				screen = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				break;
			}
		}

	}

	private void closeAll() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}

	private int duplicationCheck(String id) {
		int check = 0;
		try { 
			conn = DBUtil.getConnection();
			String nameSql = " select count(*) from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(nameSql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				check = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return check;
	}

	
	private void dataFullSelect() {
		try {
			conn = DBUtil.getConnection();
			String resultSql = "select * from mymember order by 1";
			pstmt = conn.prepareStatement(resultSql);
			rs = pstmt.executeQuery();
			System.out.println("  == 모든 값  ==");
			while (rs.next()) {
				System.out.println("아이디: " + rs.getString(1));
				System.out.println("비밀번호 : " + rs.getString(2));
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("전화번호 : " + rs.getString(4));
				System.out.println("주소 : " + rs.getString(5));
				System.out.println();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
	}
	
	private void dataUpdate() {
		System.out.println();
		String id;
		while (true) {
			conn = DBUtil.getConnection();
			int check = 0;
			System.out.print("수정할 아이디 입력 >>");
			id = scan.next();
			check = duplicationCheck(id);
			if (check != 0) {
				break;
			} else {
				System.out.println("해당 아이디가 존재하지 않습니다. 다시 입력하세요.");
			}
		}
		dataUpdataDetail(id);

	}

	private void dataUpdataDetail(String id) {
		boolean choice = true;
		String updateSql = null;
		String updateInfo = null;
		while (choice) {
			System.out.println("수정할 정보를 선택하세요");
			System.out.println("------------------------");
			System.out.println("\t 1. 비밀번호");
			System.out.println("\t 2. 이름");
			System.out.println("\t 3. 전화번호");
			System.out.println("\t 4. 주소");
			System.out.println("\t 0. 작업 끝");
			System.out.println("------------------------");
			System.out.print("작업할 번호를 입력하세요 >> ");
			String choiceNum = scan.next();
			switch (choiceNum) {
			case "1":
				System.out.print("수정할 비밀번호를 입력하세요 >>");
				updateInfo = scan.next();
				updateSql =  "update mymember set MEM_PASS = ? where MEM_ID=?";
				update(updateSql, updateInfo , id);
				break;
			case "2":
				System.out.print("수정할 이름을 입력하세요 >>");
				updateInfo = scan.next();
				updateSql =  "update mymember set MEM_NAME = ? where MEM_ID=?";
				update(updateSql, updateInfo , id);
				break;
			case "3":
				System.out.print("수정할 전화번호를 입력하세요 >>");
				updateInfo = scan.next();
				updateSql =  "update mymember set MEM_TEL = ? where MEM_ID=?";
				update(updateSql, updateInfo , id);
				break;
			case "4":
				System.out.print("수정할 주소를 입력하세요 >>");
				updateInfo = scan.next();
				updateSql =  "update mymember set MEM_ADDR = ? where MEM_ID=?";
				update(updateSql, updateInfo , id);
				break;
			case "0":
				System.out.println("수정 작업이 끝났습니다");
				choice = false;
				break;	
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				break;
				
			}
		}

	}

	private void update(String updateSql, String updateInfo, String id) {
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1, updateInfo);
			pstmt.setString(2, id);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("수정 성공!!!");
			} else {
				System.out.println("수정 실패...");
			}
		} catch (Exception e) {
			
		}finally {
			closeAll();
		}
		
	}

	private void dataDelete() {
		System.out.println();
		String id;
		while (true) {
			int check = 0;
			System.out.print("삭제할 아이디 입력 >>");
			id = scan.next();
			check = duplicationCheck(id);
			if (check != 0) {
				break;
			} else {
				System.out.println("해당 아이디가 존재하지 않습니다. 다시 입력하세요.");
			}
		}
		conn = DBUtil.getConnection();
		String deleteSql = "delete from mymember where MEM_ID=?";
		try {
			pstmt = conn.prepareStatement(deleteSql);
			pstmt.setString(1, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("삭제 성공!!!");
			} else {
				System.out.println("삭제 실패...");
			}
		} catch (Exception e) {
			
		}finally {
			closeAll();
		}
	}

	private void dataInsert() {
		System.out.println();
		String id;
	
		while (true) {
			int check = 0;
			System.out.print("아이디 입력 >>");
			id = scan.next();
			check = duplicationCheck(id);
			if (check == 0) {
				break;
			} else {
				System.out.println("해당 아이디는 이미 존재합니다. 다시 입력하세요");
			}

		}
		System.out.print("비밀번호 입력 >>");
		String pass = scan.next();
		System.out.print("이름 입력 >>");
		String name = scan.next();
		System.out.print("전화번호 입력 >>");
		String tel = scan.next();
		System.out.print("주소 입력 >>");
		String addr = scan.next();
		conn = DBUtil.getConnection();
		String insertSql = "insert into mymember values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	private String startMenu() {
		System.out.println("------------------------");
		System.out.println("\t 1. 자료 추가");
		System.out.println("\t 2. 자료 삭제");
		System.out.println("\t 3. 자료 수정");
		System.out.println("\t 4. 전체 자료 출력");
		System.out.println("\t 0. 작업 끝");
		System.out.println("------------------------");
		System.out.print("작업할 번호를 입력하세요 >> ");
		String choiceNum = scan.next();
		return choiceNum;
	}
}
