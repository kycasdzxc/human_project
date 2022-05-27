package service;

import java.io.File;
import java.util.List;

import dao.AttachDao;
import dao.BoardDao;
import dao.MemberDao;
import dao.ReplyDao;
import domain.Attach;
import domain.Criteria;
import domain.Member;

public class MemberService {
	private static MemberService memberService = new MemberService();
	public static MemberService getInstance() {
		return memberService;
	}
	private MemberService() {}
	
	private MemberDao memberDao = MemberDao.getInstance();
	private BoardDao boardDao = BoardDao.getInstacne();
	private ReplyDao replyDao = ReplyDao.getInstacne();
	private AttachDao attachDao = AttachDao.getInstacne();
	
	public List<Member> list(Criteria criteria) {
		List<Member> list = memberDao.list(criteria);
		
		list.forEach(member->{
			member.setAttach(attachDao.get(member.getId()));
		});
		
		return list;
	}
	
	public Member get(String id) {
		Member member = memberDao.get(id);
		if(member != null) {
			member.setAttach(attachDao.get(member.getId()));
		}
		return member;
	}
	
	public void register(Member member) {
		memberDao.register(member);
	}
	
	public void modify(Member member) {
		if(member.getAttach() != null) {
			if(member.getAttach().isImage()) {
				attachDao.changeNull(member.getId());
				member.getAttach().setId(member.getId());
				attachDao.insert(member.getAttach());
				memberDao.modify(member);
			}
			attachDao.insert(member.getAttach());
		}
		memberDao.modify(member);
	}
	
	public void remove(Member member) {
		boardDao.modifyNullByWriter(member.getName());
		replyDao.modifyByWriter(member.getName());
		memberDao.remove(member.getId());
	}
	
	public Member login(Member member) {
		member = memberDao.login(member.getId().toLowerCase().trim(), member.getPw().toLowerCase().trim());
		return member;
	}
	
	public void updateAuthToken(Member member) {
		memberDao.updateAuthToken(member);
	}
	
	public void updateAuth(Member member) {
		memberDao.updateAuth(member);
	}
	
	public Member findBy(String email) {
		return memberDao.findBy(email);
	}
	
	public int count(Criteria cri) {
		return memberDao.count(cri);
	}
	
	public void removePicture() {
		// 1. 첨부파일 조회
		List<Attach> attachs = attachDao.list();
		// 2. 물리적 삭제
		String saveDir = "C:\\upload";
		
		for(Attach attach : attachs) {
			if(attach.getId() == null) {
				File file = new File(saveDir, attach.getPath());			
				file = new File(file, attach.getUuid());
				file.delete();
				
				File file2 = new File(saveDir, attach.getPath());			
				file2 = new File(file2, "s_" + attach.getUuid());
				file2.delete();
			}
		}
		// 3. 첨부파일 목록 삭제
		attachDao.removeNull();
	}
 }
