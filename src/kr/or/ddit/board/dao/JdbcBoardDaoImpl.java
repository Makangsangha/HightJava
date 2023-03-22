package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.mvc.util.DBUtil;

public class JdbcBoardDaoImpl implements IJdbcBoardDao{
	// 1번
	private static JdbcBoardDaoImpl dao;
	
	// 2번
	private JdbcBoardDaoImpl() {
		
	}
	
	// 3번
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new JdbcBoardDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	//반환값이 저장될 변수
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board "
					+ " (board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ " values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	//반환값이 저장될 변수
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	//반환값이 저장될 변수
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set "
					+ " board_title = ?, board_content = ?, board_date = sysdate "
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JdbcBoardVO boardVo = null;		// 반환값 변수
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new JdbcBoardVO(); // 데이터를 저장할 VO객체 생성
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JdbcBoardVO> boardList = null;		// 반환값 변수
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = " select * from jdbc_board "
					+ " order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO(); // 데이터를 저장할 VO객체 생성
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JdbcBoardVO> boardList = null;		// 반환값 변수
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = " select * from jdbc_board "
					+ " where board_title like '%' || ? || '%' "
					+ " order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO(); // 데이터를 저장할 VO객체 생성
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; 	// 반환값 변수
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set board_cnt = board_cnt + 1"
					     + " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}	
		}
		
		return cnt;
	}

}
