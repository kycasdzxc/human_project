package works.yermi.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import works.yermi.domain.Criteria;
import works.yermi.domain.MemberVO;
import works.yermi.domain.PensionVO;
import works.yermi.domain.QnaVO;

public interface MemberService { 
	
	// 회원가입
	void join(MemberVO vo);

	// 회원 단일 조회
	MemberVO get(String userId);
	
	// 회원정보수정
	boolean modify(MemberVO memberVO);
	
	// 회원정보수정
	boolean memberListModify(MemberVO memberVO);

	// 회원탈퇴
	void remove(MemberVO memberVO);
	
	// 회원 존재여부 확인
	MemberVO hasMember(MemberVO memberVO); 
	
	// 이메일 인증
	void mailSender(String email, String userId) throws UnsupportedEncodingException, IllegalArgumentException;
	
	// 이메일 인증 확인
	boolean mailConfirm(String userId, String authToken);
	
	// 회원 목록 조회
	List<MemberVO> list(Criteria cri);
	
	// 회원 목록 총갯수
	int getTotalCount(Criteria cri);
	
	List<QnaVO> listQna(Criteria cri);
	
	int getTotalCountQna(Criteria cri);
	
	List<QnaVO> findQnaByWriter(String userId);
	
	Long register(QnaVO vo);
	
	QnaVO findBy(Long bno);

	void modifyPensionAuth(PensionVO vo);
	
}
