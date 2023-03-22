package kr.or.ddit.mvc.view;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.vo.BoardVO;

public class BoardView {
	private static BoardView view;

	private BoardView() {
		
	}
	
	public static BoardView getInstance() {
		if(view==null) {
			view = new BoardView();
		}		
		return view;
	}

	public void printStartScreen(List<BoardVO> list) {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		for(BoardVO vo : list) {
			System.out.println("   "+vo.getBoard_no()+"\t\t"+vo.getBoard_title()+"\t\t"+vo.getBoard_writer()+"\t"+vo.getBoard_cnt()+"\t");
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
	}

	public void printSelectScreen(BoardVO vo) {
		int cnt = vo.getBoard_cnt();
		System.out.println();
		System.out.println(vo.getBoard_no()+"번글 내용");
		System.out.println("-------------------------------------------------------------");
		System.out.println("- 제  목 :" + vo.getBoard_title());
		System.out.println("- 작성자 :" + vo.getBoard_writer());
		System.out.println("- 내  용 :" + vo.getBoard_content());
		System.out.println("- 작성일 :" + vo.getBoard_date());
		System.out.println("- 조회수 :" + ++cnt);
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 수정	2. 삭제		3. 리스트로 가기");
		System.out.print("작업선택 >> ");
	}


}
