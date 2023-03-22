package kr.or.ddit.mvc.vo;


import java.sql.Date;


public class BoardVO {
	private int board_no;		//게시판 번호
	private String board_title;	//게시판 제목
	private String board_writer;//게시판 작성자
	private Date board_date;    //게시판 작성 날짜
	private int board_cnt;		//게시판 조회수
	private String board_content;	//게시판 내용	
	
	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date =  board_date;
	}

	public int getBoard_cnt() {
		return board_cnt;
	}

	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

}
