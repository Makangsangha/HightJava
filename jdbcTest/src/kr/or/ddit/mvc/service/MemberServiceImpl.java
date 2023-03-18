package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDao dao;
	
	// 생성자
	public MemberServiceImpl() {
		dao = new MemberDaoImpl(); //DAO객체 생성
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo); 
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public int updateMember2(String memId, String updateField, String updateData) {
		return dao.updateMember2(memId,updateField,updateData);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember3(String id, Map<String, String> dataMap) {
		return dao.updateMember3(id, dataMap);
	}

}
