package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan;
	private IMemberService service; // Service객체 변수 선언

	public MemberController() {
		System.err.println("마강상하");
		scan = new Scanner(System.in);
		service = new MemberServiceImpl(); // Service객체 생성
	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메소드
	private int displayMenu() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("	1. 자 료 추 가");
		System.out.println("	2. 자 료 삭 제");
		System.out.println("	3. 자료수정 (전체항목수정)");
		System.out.println("	4. 전 체 자 료 출 력");
		System.out.println("	5. 자료수정2(수정항목선택)");
		System.out.println("	6. 자료수정3(입력항목만수정)");
		System.out.println("	0. 작 업 끝");
		System.out.println("----------------------------------");
		System.out.print("작업 선택 >> ");

		return scan.nextInt();
	}

	// 작업을 시작하는 메소드
	public void startMember() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:
				insertMember();
				break; // 추가
			case 2:
				deleteMeber();
				break; // 삭제
			case 3:
				updateMember();
				break; // 수정
			case 4:
				displayAllMember();
				break; // 전체 출력
			case 5:
				updateMember2();
				break; // 수정2
			case 6:
				updateMember3();
				break; // 수정3
			case 0:
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}

	private void updateMember3() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		Map<String, String> dataMap = new HashMap<>();

		System.out.println();
		scan.nextLine(); // 버퍼 지우기
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		if (!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}

		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine().trim();
		if (!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}

		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if (!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		
		System.out.print("새로운 회원주소>> ");
		String newAddr = scan.nextLine().trim();
		if (!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		int cnt = service.updateMember3(id, dataMap);

		
	}

	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int count = service.getMemberCount(memId);
		if (count == 0) {
			System.out.println(memId + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		int num;
		String updateField = null;
		String updateFiledTitle = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("---------------------------------------------------------");
			System.out.println("  1. 비밀번호     2. 회원이름    3. 전화번호    4.회원주소");
			System.out.println("---------------------------------------------------------");
			System.out.print("수정 항목을 입력하세요 >> ");
			num = scan.nextInt();

			switch (num) {
			case 1:
				updateField = "mem_pass";
				updateFiledTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateFiledTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateFiledTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateFiledTitle = "회원주소";
				break;
			default:
				System.out.println("수정 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요...");
				break;
			}

		} while (num < 1 || num > 4);

		scan.nextLine(); // 버퍼 지우기
		System.out.println();
		System.out.print("새로운 데이터 >> ");
		String updateData = scan.nextLine();
		
		int cnt = service.updateMember2(memId, updateField, updateData);
		
		if (cnt > 0) {
			System.out.println(memId + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(memId + " 회원 정보 수정 실패...");
		}
	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		MemberVO memVo = new MemberVO();
		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String pass = scan.next();

		System.out.print("새로운 회원이름 >> ");
		String name = scan.next();

		System.out.print("새로운 전화번호 >> ");
		String tel = scan.next();

		scan.nextLine();
		System.out.print("새로운 회원주소>> ");
		String addr = scan.nextLine();
		
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		int cnt = service.updateMember(memVo);
				
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 수정 실패...");
		}
	}

	private void deleteMeber() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int cnt = service.deleteMember(id);
		
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 삭제 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 삭제 실패...");
		}

	}

	private void displayAllMember() {
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("    ID      	비밀번호      이름       전화번호          주소");
		System.out.println("--------------------------------------------------------------");
		List<MemberVO> list = service.getAllMember();

		if (!list.isEmpty()) {
			for (MemberVO vo : list) {
				System.out.println(vo.getMem_id() + "\t" + vo.getMem_pass() + "\t" + vo.getMem_name() + "\t"
						+ vo.getMem_tel() + "\t" + vo.getMem_addr() + "\t");
			}
		}else {
			System.out.println("저장되어있는 사람이 없습니다.");
		}
		
		
	}

	// 자료를 추가하는 메소드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
		do {
			System.out.print("회원ID >> ");
			id = scan.next();
			count = service.getMemberCount(id);
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String pass = scan.next();

		System.out.print("회원이름 >> ");
		String name = scan.next();

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();

		// 입력한 자료를 VO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);

		// Service의 insertMember()메소드를 호출해서 실행한다.
		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println(id + " 회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 추가 실패...");
		}

	}
}
