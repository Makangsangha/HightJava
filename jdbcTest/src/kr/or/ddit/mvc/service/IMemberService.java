package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 만들어진 메소드를 원하는 작업에 맞게 호출하여 
 * 결과를 받아오고, 받아온 결과를, Controller에게 보내주는 역할을 한다.
 * 
 * (자바고급과정에서는 보통 DAO와 메소드의 구조가 같게 한다.)
 * 
 * @author PC-22
 *
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메소드
	 * 
	 * @param memVo insert할 데이터가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메소드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 삭제성공 : 1, 삭제 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO객체에 담겨진 자료를 이용하여 DB에 update하는 메소드
	 * 
	 * @param memVo update할 회원 정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메소드
	 * 
	 * @return MemberVO객체가 저장된 List객체
	 * 
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메소드
	 * 
	 * @param memID 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getMemberCount(String memId);

	/**
	 * 매개변수로 받은 Map을 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메소드 ==> 수정 항목이 1개
	 * Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memID)  
	 *
	 * 
	 * @param list 검색할 회원ID, 수정 DB문, 수정할 data를 가지고 있는 컬렉션
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);

	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 전체 수정 내역 중 선택하여 update하는 메소드
	 * Map의 정보 => key값 : 수정할 컬럼명, value값 : 수정할 데이터값
	 * 
	 * @param dataMap 수정할 정보가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember3(Map<String, String> dataMap);
}
