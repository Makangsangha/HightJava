package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	
	// 1번
	private static JdbcBoardServiceImpl service;
	
	// 2번
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	// 3번
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) {
			service = new JdbcBoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// 게시글 보기에서는 조회수를 증가하는 작업을 같이 수행해야 한다.
		int cnt = dao.setCountIncrement(boardNo);
		if(cnt==0) {  // 조회수 증가 실패!!
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {	
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
