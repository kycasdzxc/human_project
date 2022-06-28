package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utils.DBConn;

public class HTMLParserReply {
	public static void main(String[] args) throws Exception{
		/** 여기어때 페이지 연결	*/
		Connection conn = Jsoup.connect("https://www.goodchoice.kr/product/search/3/3108?sel_date=2022-06-20&sel_date2=2022-06-21");
		
		/** HTML 요소 접근을 위한 Document 객체 생성	*/
		Document doc = conn.get();
		
		/** 펜션목록을 받아올 Elements 객체 생성 */
		Elements elements = doc.select(".list_2");
		
		/** 펜션목록을 한 개씩 지정할 Element 객체 생성	*/
		for(int i = 0; i < elements.size() ; i++) {
			Element el = elements.get(i);
			
		/** 고유펜션번호를 no라는 String 타입 변수에 저장	*/
			String no = el.getElementsByTag("a").attr("data-ano");
			
		/** TBL_REPLY 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장 */
			Map<String, String> map = new HashMap<>();
			map.put("PENSIONID",no);
			
		/** 각각의 펜션 링크를 originLink라는 String 타입 변수에 저장 */
			String originLink = el.getElementsByTag("a").attr("href");
			
		/** 펜션별 JSON 데이터 링크를 headLink라는 String 타입 변수에 저장 */
			String headLink = originLink.replaceAll("detail", "get_review_non");
			
		/** 펜션별 JSON 데이터 링크의 요소 접근을 위한 Document 객체 생성	*/	
			Document doc2 = Jsoup.connect(headLink).get();
		
		/** 펜션별 JSON 데이터를 문자열로 변환해 jsonStr 이라는 String 타입 변수에 저장 */	
			String jsonStr = doc2.getElementsByTag("body").get(0).text();
			
		/** JSON 문자열을 java 객체로 변환하기 위한 Gson 객체를 생성 */	
			Gson gson = new Gson();
			
		/** 컴파일 에러가 나지 않기 위한 Type 객체 생성 */
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
		
		/** JSON 문자열을 저장하기 위한 Map 객체 생성 */	
			Map<String, Object> myMap = gson.fromJson(jsonStr, type);
			
		/**	펜션별 댓글 개수를 count 라는 String 타입 변수에 저장 */
			String count= String.valueOf(((Map)myMap.get("result")).get("count"));
		
		/** 댓글이 존재하지 않을 시엔 동작을 생략함 */	
			if (count=="0") {
				continue;
			}
			
		/**	댓글이 존재할 경우 동작을 실행함  */
			else {
		
		/** 댓글 페이지 수를 totalPageCnt 라는 String 타입 변수에 저장 */
				String totalPageCnt = String.valueOf(((Map)myMap.get("result")).get("total_page_cnt"));
		
		/** 댓글 페이지별 JSON 데이터 요소 접근을 위한 Document 객체 생성 */
				for (int j = 1; j <= Float.parseFloat(totalPageCnt); j++) {
					Document doc3 =Jsoup.connect(headLink+"&page="+j).get();
		
		/** 댓글 페이지별 JSON 데이터를 문자열로 변환해 jsonStr2 라는 String 타입 변수에 저장 */			
					String jsonStr2= doc3.getElementsByTag("body").get(0).text();
		
		/** JSON 문자열을 저장하기 위한 Map 객체 생성 */				
					Map<String, Object> myMap2 =gson.fromJson(jsonStr2, type);
					
		/** 댓글 정보 저장을 위한 배열 생성 후 for문을 이용 */			
					List<Object> items =  (List<Object>) ((Map)myMap2.get("result")) .get("items");
					for(Object item : items) {
						
						
		/** 댓글별 제목을 title 이라는 String 타입 변수에 저장 */				
						String title= String.valueOf(((Map)item).get("epilrate_textinfo"));
						
		/** 댓글별 내용을 content 라는 String 타입 변수에 저장 */				
						String content= String.valueOf(((Map)item).get("aepcont"));
						
		/** 댓글별 별점을 starRate 라는 String 타입 변수에 저장 */				
						String starRate= String.valueOf(((Map)item).get("epilrate"));
		
		/** TBL_REPLY 테이블에 정보를 저장하기 위해 Map 객체에 put을 이용한 저장 */
						map.put("starrate", starRate);
						map.put("content", content);
						map.put("title", title);
						
		/** 댓글별 등록일을 저장하기 위해 epoch 타임을 변환 */
						Long epochDate=(long)((Double)((Map)item).get("aepreg")).doubleValue();
						Instant instant = Instant.ofEpochSecond(epochDate);
						ZonedDateTime regDateTime= (ZonedDateTime.ofInstant(instant, ZoneOffset.UTC));
						
		/** 댓글별 등록일을 regDate 라는 String 타입 변수에 저장  */				
						String regDate= regDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
						
		/** TBL_REPLY 테이블에 정보를 저장하기 위해 Map 객체에 put을 이용한 저장 */				
						map.put("regDate", regDate);
						long folderName = saveDB(map);
		
		/** 이미지 정보가 존재할 시에만 실행 */				
						if (((Map)item).get("aepimg")!=null) {
							
		/** 이미지 정보 저장을 위한 배열 생성 후 for문을 이용 */								
							List<Object> images=(List<Object>)(((Map)item).get("aepimg"));
							int cnt=0;
							for (Object image : images) {
		
		/** 이미지 정보가 손상되지 않은 경우에만 실행 */
								if (((Map)image).get("aep_imgpath")!=null) {
									
		/** 댓글별 이미지 링크를 img 라는 String 타입 변수에 저장 */							
									String img="https://image.goodchoice.kr"+((Map)image).get("aep_imgpath");
									saveFile(no, folderName, img,cnt++);
									}
								}
							}
						}
					}
				}
			}
		}
	
	/** 이미지 파일들을 링크를 지정하여 저장, TBL_REPLY_ATTACH 테이블에 정보를 저장 */ 
	static void saveFile(String no, Long r_id, String imgSrc, int ORD) throws Exception {
    	int idxDot = imgSrc.lastIndexOf(".");
		String ext = "";
		if(idxDot != -1) {
			ext= imgSrc.substring(idxDot);
			System.out.println(ext);
		}
		
		String name = "";
		String path = "";
		UUID uuid = UUID.randomUUID();
		
	/** jpg 파일이 아닐 경우 dump 이미지 저장 */	
		if(!ext.equals(".jpg")) {
			name = "dump_" + uuid + ".jpg";

		} else {
			name = uuid +ext; //uuid
		
	
	  URL url = new URL(imgSrc);
      BufferedInputStream bis = new BufferedInputStream(url.openStream());
      path= "reply\\" + no+ "\\"+ r_id+"";
      File file = new File("D:\\"+path);
      if(!file.exists()) {
    	  file.mkdirs();
      }
		
		
      file =new File(file,name);
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
      
      

      int b = 0;
      while((b = bis.read()) != -1) {
          bos.write(b);
      }
      bos.close();
		}
		System.out.println(name);
      
    java.sql.Connection conn = DBConn.getConnection();
    PreparedStatement pstmt = conn.prepareStatement(
  			"INSERT INTO TBL_REPLY_ATTACH VALUES(?,?,?,1,?,?)");
    /** 이미지 파일 고유번호 */
   	pstmt.setString(1,name);
	/** 이미지 파일 원본이름 */
   	pstmt.setString(2,name);
	/** 이미지 파일 경로 */
   	pstmt.setString(3, path);
	/** 이미지 파일 순서 */
   	pstmt.setInt(4, ORD);
  	pstmt.setLong(5, r_id);
  	/** 댓글 번호 */
  	pstmt.executeUpdate();
  	pstmt.close();
	}
	
	/** TBL_REPLY 테이블에 Map 객체에 저장된 정보를 저장 */ 
	 static long saveDB(Map<String, String> map) throws Exception{
	    	Long s = 0L;
	    	java.sql.Connection conn = DBConn.getConnection();
	    	
	/** 댓글 번호 지정을 위한 시퀀스 */   	 	
	    	String sql = "SELECT SEQ_REPLY.NEXTVAL FROM DUAL";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			s = rs.getLong(1);
	    	
			conn = DBConn.getConnection();
	    	pstmt = conn.prepareStatement(
	    			"INSERT INTO TBL_REPLY VALUES(?,?,?,NULL,?,?,?)");
	    	pstmt.setString(1, map.get("PENSIONID"));
	    	pstmt.setLong(2, s);
	    	pstmt.setString(3, map.get("starrate"));
	    	pstmt.setString(4, map.get("regDate"));
	    	pstmt.setString(5, map.get("content"));
	    	pstmt.setString(6, map.get("title"));
	    	pstmt.executeUpdate();
	    	pstmt.close();
	    	
	    	return s;
    }
}