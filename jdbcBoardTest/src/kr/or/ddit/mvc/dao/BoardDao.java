package kr.or.ddit.mvc.dao;

import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;

public interface BoardDao {
	/**
	 * 현재 DB에 저장되어있는 Board를 모두 출력하는 메소드
	 * 
	 * @return 검색된 게시판의 개수
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 게시판을 작성하여 DB에 저장하는 메소드
	 * 
	 * @param boardVo insert될 VO객체
	 * @return 작업 성공 : 1 / 작업 실패 : 0 반환 
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 특정 게시판을 선택하여 해당 내용을 보여주는 메소드
	 * 
	 * @param board_no 검색한 게시판의 번호
	 * @return 해당 게시판 전체 내용 반환
	 */
	public BoardVO selectBoard(int board_no);
	
	/**
	 * 특정 게시판을 선택하여 해당 게시판의 내용을 수정하는 메소드
	 * 
	 * @param boardVo 수정할 게시판의 제목, 내용
	 * @return 작업 성공 : 1 / 작업 실패 : 0 반환
	 */
	public int updateBoard(BoardVO boardVo);
	
	
	/**
	 * 특정 게시판을 선택하여 해당 게시판의 내용을 삭제하는 메소드
	 * 
	 * @param board_no 삭제할 게시판의 번호
	 * @return 작업 성공 : 1 / 작업 실패 : 0 반환
	 */
	public int deleteBoard(int board_no);
	
	
	/**
	 * 해당 게시글이 존재하는지 존재 유무 확인하는 메소드
	 * 
	 * @param board_no 조회한 게시판 번호
	 * @return 해당 게시판 번호 반환
	 */
	public int getBoardCount(int board_no);
	
	/**
	 * 해당 게시글을 봤으면 조회수를 +1 업데이트 해주는 메소드
	 * 
	 * @param boardVo 올라간 조회수 및 ID정보
	 * @return 작업 성공 : 1 / 작업 실패 : 0 반환
	 */
	public int updateCnt(BoardVO boardVo);
}
