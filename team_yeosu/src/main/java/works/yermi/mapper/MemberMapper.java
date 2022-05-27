package works.yermi.mapper;

import java.util.List;
import java.util.Map;

import works.yermi.domain.AuthVO;
import works.yermi.domain.Criteria;
import works.yermi.domain.MemberVO;
import works.yermi.domain.QnaVO;

public interface MemberMapper {
	// 회원 단일 조회
	MemberVO read(String userId);
	
	// 회원 목록 조회
	List<MemberVO> readList(Criteria cri); 
	
	// 회원 목록 총갯수
	public int getTotalCount(Criteria cri);
	
	// <회원가입>
	// 회원 생성
	int insertMember(MemberVO vo);
	// 권한 생성
	int insertAuth(AuthVO vo);
	
	// <회원정보수정>
	// 회원 수정
	int updateMember(MemberVO vo);
	// 권한 수정
	int updateAuth(AuthVO vo);
	// 회원 수정 (관리자)
	int updateMemberList(MemberVO vo);
	
	// <회원탈퇴>
	// 회원 삭제
	boolean deleteMember(MemberVO vo);
	// 권한 삭제
	int deleteAuth(String userId);
	
	// <회원 존재여부 확인>
	MemberVO hasMember(MemberVO memberVO); 
	
	// <회원 수정 여부 확인>
	MemberVO hasMemberUsingModify(MemberVO memberVO); 
	
	// 이메일 권한 부여
	int updateAuthEmail(MemberVO vo);
	
	// 이메일 인증 토큰 부여
	int updateAuthToken(MemberVO vo);
	
	List<QnaVO> listQna(Criteria cri);
	
	List<QnaVO> findQnaByWriter(String userId);
	
	Long register(QnaVO vo);
	
	QnaVO findBy(Long bno);
	
	int getTotalCountQna(Criteria cri);
}
