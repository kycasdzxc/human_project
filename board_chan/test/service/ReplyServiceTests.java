package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Reply;
import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyServiceTests {
	private ReplyService replyService = ReplyService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(replyService);
	}
	
	@Test
	public void testList() {
		replyService.list(162L).forEach(log::info);
	}
	
	@Test
	public void testRegister() {
		for(int i = 900 ; i < 912 ; i++) {
			int r = (int)(Math.random() * 100) % 5 +1;
			
			switch(r) {
			case 1:
				Reply reply = new Reply(null, "날씨가 너무 좋다~ 딩동댕", null, (long)i, "미첼");
				replyService.register(reply);
				reply = new Reply(null, "나랑 술래잡기 할 사람~ 땡땡!", null, (long)i, "드리미");
				replyService.register(reply);
				reply = new Reply(null, "다들 행복한 하루 되세요~ 우왕", null, (long)i, "잭슨");
				replyService.register(reply);
				reply = new Reply(null, "오늘 하루도 잘 부탁하지. 안그냐", null, (long)i, "피터");
				replyService.register(reply);
				break;
			case 2:
				reply = new Reply(null, "으아앗!! 요리학원에 늦어버렸다.. 그쵸", null, (long)i, "킹");
				replyService.register(reply);
				reply = new Reply(null, "음.. 아끼는 책을 잃어버렸다... 근데", null, (long)i, "참돌이");
				replyService.register(reply);
				reply = new Reply(null, "생일 축하해줘서 고마워! 됐거든", null, (long)i, "호랭이");
				replyService.register(reply);
				reply = new Reply(null, "이몸이 운동 꿀팁을 알려주지! 그러마", null, (long)i, "마리");
				replyService.register(reply);
				break;
			case 3:
				reply = new Reply(null, "오늘은 내가 패션피플~ 동글", null, (long)i, "빙수");
				replyService.register(reply);
				reply = new Reply(null, "내가 문제 하나 내볼까? 힐끔힐끔", null, (long)i, "곤잘레스");
				replyService.register(reply);
				reply = new Reply(null, "나랑 술래잡기 할 사람~ 땡땡!", null, (long)i, "조르쥐");
				replyService.register(reply);
				reply = new Reply(null, "다들 행복한 하루 되세요~ 우왕", null, (long)i, "존");
				replyService.register(reply);
				break;
			default:
				reply = new Reply(null, "오늘은 내가 패션피플~ 동글", null, (long)i, "햄까스");
				replyService.register(reply);
				reply = new Reply(null, "내가 문제 하나 내볼까? 힐끔힐끔", null, (long)i, "애플");
				replyService.register(reply);
				reply = new Reply(null, "나랑 술래잡기 할 사람~ 땡땡!", null, (long)i, "탁호");
				replyService.register(reply);
				reply = new Reply(null, "다들 행복한 하루 되세요~ 우왕", null, (long)i, "원승");
				replyService.register(reply);
				break;
			}
		}
	}
	
	@Test
	public void testRemove() {
		Long rno = 61L;
		replyService.remove(rno);
	}
}
