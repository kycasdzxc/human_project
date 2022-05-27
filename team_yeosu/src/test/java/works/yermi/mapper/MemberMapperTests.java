package works.yermi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.domain.AuthVO;
import works.yermi.domain.Criteria;
import works.yermi.domain.MemberVO;
import works.yermi.domain.QnaVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
	RootConfig.class, SecurityConfig.class
})

@Log4j
public class MemberMapperTests {
	@Setter @Autowired
	private PasswordEncoder encoder;
	@Setter @Autowired 
	private MemberMapper mapper;
	
	@Test // 회원 정보 조회
	public void testRead(){
		log.info(mapper.read("admin1"));
	}

	// <회원가입>
	@Test // 회원 생성
	public void testInsertMember(){
		MemberVO vo = new MemberVO();
		vo.setUserId("javaadmin");
		vo.setPw(encoder.encode("1234"));
		vo.setEmail("ccc@javaman.co.kr");
		vo.setPhone("010");
		vo.setPhone2("1234");
		vo.setPhone3("5678");
		vo.setRoadAddr("서울특별시 영등포구 영중로8길 6");
		vo.setAddrDetail("휴먼교육센터");
		vo.setZipNo("07302");
		vo.setJibunAddr("서울특별시 영등포구 영등포동3가 8-1 성남빌딩");
		vo.setName("자바관리자");
		vo.setNickName("자바관리자");
		
		log.info(vo);
		mapper.insertMember(vo);
		
//		String[] memberArr = {"이대석", "김예찬", "류윤정", "김동엽", "김상현", "김경보",
//				"황규웅", "김태윤", "김승종", "이나현", "김치형", "차민지", "이연주", "박상근", "이강민"};
//		String[] memberArr2 = {"달래99", "주유중임", "초록색나팔꽃", "NKB79", "동글이85",
//				"수경리대섴", "김포마우스", "보리캔디", "서계서원병풍", "건강지키자",
//				"홍대한치오징어", "김돌게장", "셋상남자", "영원한레인", "양퓨퓨",
//				"hhanhee", "예르미티챠", "tnswlsdldia", "가냘픈만두", "쉰셋병장",
//				"윤댕댕", "송유제니", "친숙한흡혈동물", "난이쁜다행", "뿌링클클끌",
//				"쌍문동꿀단지", "몽땅2", "정댕쥐", "an9882", "inadang",
//				"오송강", "10604445", "신난다용", "시크릿하고", "김서영14",
//				"석잉이잉", "일산베어스s", "꼬마야놀자", "칠구와칠칠이", "얄릴로",
//				"겸손한투계", "열여섯째포탑", "편안한브루스", "bibibik", "태용씨",
//				"꿈꾸는아지", "chshk", "광적인화상", " 찐득한암랑", "anadao"};
//		IntStream.rangeClosed(1, 50).forEach(r -> {
//			MemberVO vo = new MemberVO();
//			vo.setUserId("user" + r);
//			vo.setNickName(memberArr2[r-1]);
//			vo.setName(memberArr[(int) (Math.random() * memberArr.length)]);
//			vo.setPw(encoder.encode("1234"));
//			mapper.insertMember(vo);
//		});
	}

	@Test // 권한 생성
	public void testInsertAuth(){
		AuthVO vo = new AuthVO();
		
//		vo.setAuth("ROLE_MEMBER");
//		vo.setAuth("ROLE_MANAGER");
		vo.setAuth("ROLE_ADMIN");
		vo.setUserId("javaadmin");
		mapper.insertAuth(vo);
//		
		
//		IntStream.range(1, 51).forEach(r -> {
//			AuthVO vo = new AuthVO();
//			vo.setUserId("user" + r);
//			vo.setAuth("ROLE_MEMBER");
//			mapper.insertAuth(vo);
//		});
		
	}
	
	// <회원정보수정>
	@Test // 회원 수정
	public void testUpdateMember(){
		MemberVO vo = mapper.read("admin5");
		vo.setPw(encoder.encode("1234"));
//		vo.setEmail("sample23@yermi.works");
//		vo.setPhone("010");
//
//		vo.setPoint(1);
//		
//		vo.setRoadAddr("서울특별시 영등포구 영중로8길 6");
//		vo.setAddrDetail("휴먼교육센터");
//		vo.setZipNo("07302");
//		vo.setJibunAddr("서울특별시 영등포구 영등포동3가 8-1 성남빌딩");
//		vo.setName("관리자1");
//		vo.setNickName("관리자1닉");
//		vo.setPhone2("1234");
//		vo.setPhone3("4567");
//		
//		vo.setUserId("admin1");

		mapper.updateMember(vo);
		log.info(vo);
	}

	@Test // 권한 수정
	public void testUpdateAuth(){
		AuthVO vo = new AuthVO();
		vo.setAuth("ROLE_MEMBER");
//		vo.setAuth("ROLE_MANAGER");
//		vo.setAuth("ROLE_ADMIN");
		
		int result = mapper.updateAuth(vo);
		
		log.info(result);
		log.info(vo);
	}
	
	// <회원탈퇴>
	@Test // 회원 삭제
	public void testDeleteMember(){
		MemberVO vo = new MemberVO();
		vo.setUserId("javaman");
		vo.setDelStatus(false);
		vo.setEmail("sample27@yermi.works ");
		boolean result = mapper.deleteMember(vo);
		log.info(result);
		log.info(vo);
	}

	@Test // 권한 삭제
	public void testDeleteAuth(){
		mapper.deleteAuth("javaman");
	}
	
	@Test
	public void testHasmember(){
		
	}
	
	@Test
	public void testAuth() {
//		String val1 = "$2a$10$uP.dTXE6FUHv0za0p0L9SuywaDjaYkHiD5n3PlHaLdS.xQljfh252";
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		cri.setPageNum(2);
		mapper.readList(cri).forEach(log::info);;
	}
	
	@Test
	public void testListQna() {
		Criteria cri = new Criteria();
		cri.setPageNum(1);
		mapper.listQna(cri).forEach(log::info);
	}
	
	@Test
	public void testFindQnaByWriter() {
		mapper.findQnaByWriter("javaman").forEach(log::info);
	}

	@Test
	public void testFindBy() {
		log.info(mapper.findBy(22L));
	}
	@Test
	public void testRegister() {
		QnaVO qnaVO = new QnaVO();
//		qnaVO.setWriter("javaman");
//		qnaVO.setTitle("매퍼테스트 제목");
//		qnaVO.setContent("매퍼테스트 내용");
		qnaVO.setWriter("admin1");
		qnaVO.setTitle("매퍼테스트 답변 제목");
		qnaVO.setContent("매퍼테스트 답변 내용");
		qnaVO.setGroupno(21L);
		mapper.register(qnaVO);
		
		log.info(qnaVO.getBno());
		
	}

}
