package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.DBConn;

public class HTMLParserRoom {
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
			
		/** 각각의 펜션 링크를 link라는 String 타입 변수에 저장	*/
			String link = el.getElementsByTag("a").attr("href");
			
		/** 각각의 펜션 링크의 HTML 요소 접근을 위한 Document 객체 생성	*/
			Document doc2 = Jsoup.connect(link).get();
		
		/** 각각의 펜션 숙소별 정보를 받아올 Elements 객체 생성 */	
			Elements roomInfo = doc2.select(".room");
			
		/** 각각의 펜션 숙소별 정보를 한 개씩 지정할 Element 객체 생성 */
			for (int j = 0; j < roomInfo.size(); j++) {
				Element el2= roomInfo.get(j);
					
		/**	각각의 펜션 숙소별 가격을 price 라는 int 타입 변수에 저장 
		 *	.equals("") ? "0" : el2.select(".price p b").text().replace(",", "").replace("원", "")) 는 가격정보에 결함이 있을 경우를 위함 
		 */			
				int price = Integer.parseInt(el2.select(".price p b").text().equals("") ? "0" : el2.select(".price p b").text().replace(",", "").replace("원", ""));
					
		/**	각각의 펜션 숙소별 숙소명을 roomName 이라는 String 타입 변수에 저장 */
				String roomName= roomInfo.select(".title").get(j).text();
		
		/** TBL_ROOM 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장 */		
				Map<String, String> map = new HashMap<>();
				map.put("PENSIONID",no);
				map.put("ROOMNAME", roomName);
				Map<String, Integer> map2 = new HashMap<>();
				map2.put("PRICE",price);
				
		/** 이미지 파일 저장을 위한 folderName 이라는 long 타입 변수를 선언 */
				long folderName = saveDB(map, map2);
		
		/** 각각의 펜션 숙소별 이미지들을 받아오기 위한 Elements 객체 생성 */
				Elements el3= el2.select(".item");
				
		/** 각각의 펜션 숙소별 이미지 정보를 한 개씩 지정할 Element 객체 생성 */		
				for (int k = 0; k < el3.size(); k++) {
					Element eximg2 = el3.get(k);
					
		/** 각각의 펜션 숙소별 이미지 링크들을 img 라는 String 타입 변수에 저장 */			
					String img = "https:"+ eximg2.select("img").attr("data-src");
					saveFile(no, folderName, img,k);
				}
				
			}
			
			System.out.printf(no + "번 작업 완료" +"\n"+"\n");
		}
	}
	
    /** 이미지 파일들을 링크를 지정하여 저장, TBL_ROOM_ATTACH 테이블에 정보를 저장 */ 
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
       path= "room\\" + no+ "\\"+ r_id+"";
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
   			"INSERT INTO TBL_ROOM_ATTACH VALUES(?,?,?,1,?,?)");
    /** 이미지 파일 고유번호 */
   	pstmt.setString(1,name);
	/** 이미지 파일 원본이름 */
   	pstmt.setString(2,name);
	/** 이미지 파일 경로 */
   	pstmt.setString(3, path);
	/** 이미지 파일 순서 */
   	pstmt.setInt(4, ORD);
   	/** 객실 번호 */
   	pstmt.setLong(5, r_id);
   	pstmt.executeUpdate();
   	pstmt.close();
 }
  
	 /** TBL_ROOM 테이블에 Map 객체에 저장된 정보를 저장 */ 	 
	 static long saveDB(Map<String, String> map, Map<String, Integer> map2) throws Exception{
	    	Long s = 0L;
	    	java.sql.Connection conn = DBConn.getConnection();
	    	
	    	/** 객실 번호 지정을 위한 시퀀스 */   	
	    	String sql = "SELECT SEQ_ROOM.NEXTVAL FROM DUAL";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			s = rs.getLong(1);
	    	
			conn = DBConn.getConnection();
	    	pstmt = conn.prepareStatement(
	    			"INSERT INTO TBL_ROOM VALUES(?,?,NULL,NULL,0,?,1,?)");
	    	pstmt.setString(1, map.get("PENSIONID"));
	    	pstmt.setLong(2, s);
	    	pstmt.setInt(3, map2.get("PRICE"));
	    	pstmt.setString(4, map.get("ROOMNAME"));
	    	pstmt.executeUpdate();
	    	pstmt.close();
	    	
	    	return s;
	    }
}