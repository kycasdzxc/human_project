package works.yermi.mapper;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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
public class ReplyMapperTests {

//	private Long[] bnoArr = {70384L, 69903L, 69631L, 68868L, 68590L, 68589L, 58134L};
	
	@Autowired @Setter
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
		log.info("mapper : " + mapper);
	}
	
	//	ReplyVO read(long rno);
	@Test
	public void testRead() { // 특정 댓글 번호로 댓글 조회
		ReplyVO vo = mapper.read(32L);
		log.info(vo);
	}

	//	public List<ReplyVO> getList();
	@Test
	public void testGetList() { // 특정 펜션의 댓글목록
		CriteriaReply cri = new CriteriaReply();
		cri.setLastRno(1L);
		cri.setAmount(10);
		mapper.getListWithPaging(60680L, cri);
	}
	
	//	ReplyVO get(String userid); 
	@Test
	public void testGet(){ // 특정 아이디 사용자가 쓴 댓글목록
		ReplyVO vo = new ReplyVO();
		vo.setNickName("id1");
		mapper.get(vo.getNickName()).forEach(log::info);
	}
	
	//	int insert(ReplyVO vo);
	@Test
	public void testInsert() {
		ReplyVO vo = new ReplyVO();
		vo.setPensionid(70384L);
		vo.setStarRate(5);
		vo.setNickName("id1");
		vo.setContent("겁나게 좋아유~");
		vo.setTitle("아따아따 좋은곳");
		log.info(vo);
		
	//	mapper.getList(vo);
		mapper.insert(vo);
		log.info(vo);
	}

	//	int insertSelectKey(ReplyVO vo);
	@Test
	public void testInsertSelectKey() {
		ReplyVO vo = new ReplyVO();
		vo.setPensionid(70384L);
		vo.setStarRate(5);
		vo.setNickName("test");
		vo.setContent("겁나게 좋아유~22");
		vo.setTitle("아따아따 좋은곳 SELECT KEY~");
		log.info(vo);
//		mapper.getList(vo);
		mapper.insertSelectKey(vo);
		log.info(vo);
	}

	//	int delete(long rno);
	@Test
	public void testDelete() {
		mapper.delete(29L);
	}
	

	//	int update(ReplyVO replyVO);
	@Test
	public void testUpdate() {
		ReplyVO vo = mapper.read(57L);
		vo.setTitle("수정테스트");
		vo.setContent("수정 내용");
		vo.setStarRate(4);
		
		mapper.update(vo);
	}
	
	// Map<String, Object> readStarRate(Long pensionid);
	@Test
	public void tesetReadStarRate() {
		mapper.readStarRate(68459L);
	}
	
	@Test
	public void testCreate() {
		File file = new File("C:/pension/reply/");
		String[] path = file.list();
		log.info(Arrays.toString(file.list()));
		log.info(file.list()[0]);
		File file2 = new File(file, file.list()[0]);
		log.info(file2);
	}
	
	@Test
    public void testReadFile() {
       String fileDir = "C:/pension/";
       
       List<Map<String, Object>> list = mapper.fileTransfer();
       String root = "C:/pension/reply/";
       File file = new File(root);
       String[] path = file.list();
       
       for(int i = 37 ; i < path.length ; i++) { 
    	  String x = path[i];
    	  log.info("ID :: " + x);
    	  log.info("ID2 :: " + list.get(i).get("PENSIONID").toString());
    	  
    	  File x2 = new File(root + path[i]);
    	  String[] e = x2.list();
    	  
    	  for(int j = 0 ; j < e.length ; j++) {
    		  log.info(e[j]);
    		  String e2 = e[j];
    		  File x3 = new File(root + "/" + path[i], e2);
    		  log.info(Arrays.toString(x3.list()));
    		  
    		  for(int z = 0 ; z < x3.list().length ; z++) {
    			  
	    		  File file2 = new File(root + "/" + path[i] + "/" + e2, x3.list()[z]);
	    		  log.info("x3.list()[z] :: " + x3.list()[z]);
	    		  log.info("file2 :: " + file2);
	    		  File target = null;
	    		  for(int y = 0 ; y < list.size() ; y++) {
	    			  if(x3.list()[z].equals(list.get(y).get("UUID").toString())){
	    				  target = new File(root + "/reply_re", list.get(y).get("PENSIONID").toString() + "/" + list.get(y).get("RNO").toString());
	    				  break;
	    			  }
	    		  }
	    		  log.info(file2.exists());
	    		  log.info(target);
	    		  log.info(i + " / " + j + " / " + z);
	    		  target.mkdirs();
	    		  if(target == null) {
	    			  break;
	    		  }
	    		  try {
	    			  if(file2.exists())
	    				  FileUtils.copyFileToDirectory(file2, target);
	    		  } catch (IOException e3) {
	    			  // TODO Auto-generated catch block
	    			  e3.printStackTrace();
	    		  }
    		  }
    	  }
    	      	  
       }
       
       /*mapper.fileTransfer().forEach(o->{
         
          File file = new File(fileDir, o.get("R").toString());
          File target = new File(fileDir + "/reply_re", o.get("PENSIONID").toString());
          try {
             if(file.exists())
             FileUtils.copyDirectory(file, target);
          } catch (IOException e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
          }
       });*/
    }
	
}
