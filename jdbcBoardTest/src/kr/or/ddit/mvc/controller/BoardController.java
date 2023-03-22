package kr.or.ddit.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.BoardServiceImpl;
import kr.or.ddit.mvc.view.BoardView;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardController {
	private BoardView view;
	private Scanner scan;
	private BoardServiceImpl service;
	private BoardVO vo;
	private List<BoardVO> list;

	public static void main(String[] args) {
		new BoardController().startBoard();
	}

	public BoardController() {
		view = BoardView.getInstance();
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
		vo = new BoardVO();
		list = new ArrayList<>();
	}

	private void startBoard() {
		list = service.getAllBoard();
		while (true) {
			view.printStartScreen(list);
			String choice = scan.next();
			switch (choice) {
			case "1":
				insertBoard();
				list = service.getAllBoard();
				break;
			case "2":
				selectBoard();
				list = service.getAllBoard();
				break;
			case "3":
				list = selectTitle();
				break;
			case "0":
				System.out.println("게시판 프로그램 종료....");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}
		}

	}

	private List<BoardVO> selectTitle() {
		scan.nextLine();
		System.out.println("");
		System.out.println("검색 작업");
		System.out.println("--------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String title = scan.nextLine();
		
		if(title.equals("")) {
			list = service.getAllBoard();
			return list;
		}
		
		List<BoardVO> list2 = new ArrayList<>();
		
		for(BoardVO vo : list) {
			if(vo.getBoard_title().contains(title)) {
				list2.add(vo);
			}
		}
		
		return list2;
	
	}

	private void selectBoard() {
		int choice = 0;
		while (true) {
			
			System.out.print("보기를 원하는 게시물 번호 입력 >> ");
			choice = scan.nextInt();
			int cnt = service.getBoardCount(choice);

			if (cnt <= 0) {
				System.out.println("해당 게시글 번호는 존재하지 않습니다.");
				System.out.println("다시 입력하세요");
			} else {
				break;
			}
		}
		vo = service.selectBoard(choice);
		boolean start = true;
		while(start) {
			view.printSelectScreen(vo);
			String choice2 = scan.next();
			switch (choice2) {
			case "1":
				updateBoard();
				break;
			case "2":
				deleteBoard(vo.getBoard_no());
				start = false;
				break;
			case "3":
				System.out.println("게시글로 돌아갑니다.");
				start = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				break;
			}
		}
		service.updateCnt(vo);
		
		
	}

	private void deleteBoard(int board_no) {
		int cnt = service.deleteBoard(board_no);

		if (cnt > 0) {
			System.out.println(board_no + "번글이 삭제되었습니다.");
		} else {
			System.out.println("게시글 삭제실패...");
		}
		
	}

	private void updateBoard() {
		scan.nextLine();
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("--------------------------------");
		System.out.print("- 제  목 :");
		String title = scan.nextLine();
		System.out.print("- 내  용 :");
		String content = scan.nextLine();
		
		vo.setBoard_title(title);
		vo.setBoard_content(content);
		
		int cnt = service.updateBoard(vo);

		if (cnt > 0) {
			System.out.println("게시글 수정완료!!!");
		} else {
			System.out.println("게시글 수정실패...");
		}
		
	}

	private void insertBoard() {
		System.out.println();
		scan.nextLine();
		System.out.println("새글 작성하기");
		System.out.println("--------------------------------");
		System.out.print("- 제 목  :");
		String title = scan.nextLine();
		System.out.print("- 작성자 :");
		String writer = scan.nextLine();
		System.out.print("- 내 용  :");
		String content = scan.nextLine();
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(content);

		int cnt = service.insertBoard(vo);

		if (cnt > 0) {
			System.out.println("게시글 작성완료!!!");
		} else {
			System.out.println("게시글 작성실패...");
		}
	}

}
