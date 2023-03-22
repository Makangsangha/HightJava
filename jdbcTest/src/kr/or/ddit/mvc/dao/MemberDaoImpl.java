package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl  implements IMemberDao {
	
	private static MemberDaoImpl dao;
	
	
	private MemberDaoImpl() {
		
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ " values ( ?, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 변환값이 저장될 변수

		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();

			String sql = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>(); // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			MemberVO vo = null;
			while (rs.next()) {
				vo = new MemberVO(); // 1개의 레코드가 저장될 VO객체 생성

				// ResultSet의 데이터를 꺼내와 VO객체를 셋팅한다.
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pass(rs.getString("mem_pass"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_tel(rs.getString("mem_tel"));
				vo.setMem_addr(rs.getString("mem_addr"));

				// VO객체를 List에 추가한다.
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언

		try {
			conn = DBUtil3.getConnection();

			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}

		return count;
	}

	
	//Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memID)  
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " + paramMap.get("field") + " = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnection();

			String temp = ""; // SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수

			/*
			 * for(String fieldName : dataMap.keySet()) { 
			 *   if (!"memId".equals(fieldName)) {
			 *    if(!"".equals(temp)) {
			 *     temp += ", "; }
			 *     
			 *  emp += fieldName + " = '" + dataMap.get(fieldName) +"'";} }
			 * 
			 * String sql = "update mymember set " + temp + " where mem_id = ? "; pstmt =
			 * conn.prepareStatement(sql); pstmt.setString(1, id);
			 * 	pstmt.setString(num, dataMap.get("memId"));
			 */

			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) { // Map의 key값 중 'memId'는 검색할 ID값에 대한 정보이기 때문에 수정할 커럼을 설정할 때는 포함하지 않는다.
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ?";
				}
			}

			String sql = "update mymember set " + temp + " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) {
					pstmt.setString(num++, dataMap.get(fieldName));
				}
			}
			pstmt.setString(num, dataMap.get("memId"));

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(dataMap.get("memId") + " 회원 정보 수정 완료!!!");
			} else {
				System.out.println(dataMap.get("memId") + " 회원 정보 수정 실패...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return cnt;
	}

}
