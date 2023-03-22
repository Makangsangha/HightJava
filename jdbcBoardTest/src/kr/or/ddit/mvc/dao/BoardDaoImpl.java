package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.util.DBUtil;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardDaoImpl implements BoardDao {
	private static BoardDaoImpl dao;

	private BoardDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static BoardDaoImpl getInstance() {
		if (dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board order by 1 desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int board_no = rs.getInt(1);
				String board_title = rs.getString(2);
				String board_writer = rs.getString(3);
				Date board_date = rs.getDate(4);
				int board_cnt = rs.getInt(5);
				String board_content = rs.getString(6);
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setBoard_writer(board_writer);
				vo.setBoard_date(board_date);
				vo.setBoard_cnt(board_cnt);
				vo.setBoard_content(board_content);
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
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
			if(rs!=null)
				try {
					rs.close();
				} catch (Exception e2) {
				}

		}

		return list;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board values (board_seq.nextVal, ?, ?, sysdate,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());

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
	public BoardVO selectBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "  select * from jdbc_board where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			    vo = new BoardVO();

				String board_title = rs.getString(2);
				String board_writer = rs.getString(3);
				Date board_date = rs.getDate(4);
				int board_cnt = rs.getInt(5);
				String board_content = rs.getString(6);
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setBoard_writer(board_writer);
				vo.setBoard_date(board_date);
				vo.setBoard_cnt(board_cnt);
				vo.setBoard_content(board_content);

			}
			
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
		
		return vo;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
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
	public int deleteBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);

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
	public int getBoardCount(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언

		try {
			conn = DBUtil.getConnection();

			String sql = "select count(*) cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);

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

	@Override
	public int updateCnt(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_cnt = ? where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardVo.getBoard_cnt()+1);
			pstmt.setInt(2, boardVo.getBoard_no());
			
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

}
