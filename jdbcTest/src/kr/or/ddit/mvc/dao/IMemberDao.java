package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 
 * Service에게 전달하는 DAO의 interface
 * 
 * 하나의 메소드는 DB와 관련된 작업을 1개를 수행하도록 작성한다.
 * 
 * @author PC-22
 *
 */
public interface IMemberDao {
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
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 특정 부분만 update하는 메소드
	 * 
	 * @param memId 검색할 회원ID 
	 * @param updateField 수정해야할 DB문
 	 * @param updateData 수정할 data
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(String memId, String updateField, String updateData);
	
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 전체 수정 내역 중 선택하여 update하는 메소드
	 * 
	 * @param memId 검색할 회원ID 
	 * @param dataMap 특정 부분만 수정 가능하도록 하는 data
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember3(String id, Map<String, String> dataMap);
}


