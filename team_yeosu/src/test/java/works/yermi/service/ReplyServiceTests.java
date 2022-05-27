package works.yermi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.domain.CriteriaReply;
import works.yermi.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class ReplyServiceTests {
	@Setter @Autowired
	private ReplyService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	//	ReplyVO get(Long rno);
	@Test
	public void testGet() { // 특정목록
		ReplyVO vo = service.get(39081L);
		log.info(vo);
	}
	
	//	List<ReplyVO> getList();
	@Test
	public void testGetList() { // 전체목록
		ReplyVO vo = new ReplyVO();
		CriteriaReply cri = new CriteriaReply();
		vo.setPensionid(67180L);
		cri.setLastRno(1L);
		cri.setAmount(10);
		service.getList(vo.getPensionid(), cri);
	}
	
	//	List<ReplyVO> getListByUser(String userid);
	@Test
	public void testGetListByUser() { // 특정 id로 댓글조회
		ReplyVO vo = new ReplyVO();
		vo.setNickName("id1");
		service.getListByUser(vo.getNickName()).forEach(log::info);
	}
	
	
	//	int register(ReplyVO vo);
	@Test
	public void testRegister() { // 댓글 등록
		ReplyVO vo = new ReplyVO();
		
		vo.setTitle("service test");
		vo.setContent("service content");
		vo.setNickName("id1");
		vo.setStarRate(5);
		vo.setPensionid(57329L);
		log.info(vo);
		
		int exp = 1;
		int result = service.register(vo);
		
		assertEquals("게시글추가", exp, result);
	}
	
	//	int modify(ReplyVO vo);
	@Test
	public void testModify() { // 댓글 수정
		ReplyVO vo = service.get(103L);
		log.info(vo);
		vo.setTitle("수정해불것이여");
		log.info(vo);
		
		boolean exp = true;
		int result = service.modify(vo);
		
		assertEquals("게시글 수정", exp, result);
		log.info(vo);
		
	}
	
	//	int remove(Long rno);
	@Test
	public void testRemove() {
		service.remove(60L);
	}
	
	// console.log("==============JS TEST=====================")
   // 댓글등록
   // replyService.add({pensionid:"6867", title:"댓글을 달아본다.", content:"그렇다", starRate:"10", nickName:"김돌게장"}, function(result) {
   // 	console.log("등록결과 : " + result);
   // });
 	
   // 댓글삭제
   // replyService.remove({rno:"108"}, function(result) {
   // 	console.log("삭제결과 : " + result);
   // });
    
   // 댓글 수정
   // replyService.update({rno:"32109", starRate:"10", pensionid:"10358", nickName:"동글이85", title:"변경되라 얍", content:"변경된 내용"}, function(result) {
   // 	console.log("수정결과" + result);
   // }) 
	
}
