package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.BoardDaoImpl;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private static BoardServiceImpl service;

	private BoardServiceImpl() {

	}

	public static BoardServiceImpl getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public int insertBoard(BoardVO memVo) {
		return dao.insertBoard(memVo);
	}

	@Override
	public BoardVO selectBoard(int board_no) {
		return dao.selectBoard(board_no);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}


	@Override
	public int getBoardCount(int board_no) {
		return dao.getBoardCount(board_no);
	}

	@Override
	public int updateCnt(BoardVO boardVo) {
		return dao.updateCnt(boardVo);
	}

}
