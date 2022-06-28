package app;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.DBConn;

public class HTMLParserFilter {
	public static void main(String[] args) throws Exception{
		/** 여기어때 페이지 연결	*/
		Connection conn = Jsoup.connect("https://www.goodchoice.kr/product/search/3/3108?sel_date=2022-06-20&sel_date2=2022-06-21");
		
		/** HTML 요소 접근을 위한 Document 객체 생성	*/
		Document doc = conn.get();
		
		/** 펜션목록을 받아올 Elements 객체 생성	*/
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
			
		/** 편의시설 및 서비스 정보를 받아올 Elements 객체 생성	*/
			Elements el2=doc2.select(".service");
			
		/** .equals("") ? "" : "1" 은 편의시설 및 서비스 정보를 0 또는 1로 저장하기 위함	*/
			
		/** 픽업 가능 여부 */
			String pickup=	el2.select(".theme_62").text().equals("") ? "" : "1";
		/** 취사 가능 여부 */
			String cooking=	el2.select(".theme_237").text().equals("") ? "" : "1";
		/** 조식 포함 여부 */
			String BREAKFAST=	el2.select(".theme_208").text().equals("") ? "" : "1";
		/** 주차 가능 여부 */
			String FREE_PARKING=	el2.select(".theme_147").text().equals("") ? "" : "1";
		/** 캠프파이어 가능 여부 */
			String CAMPFIRE=	el2.select(".theme_330").text().equals("") ? "" : "1";
			
		/** TBL_OTHER 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장 */
			Map<String, String> other = new HashMap<>();
			other.put("pickup",pickup);
			other.put("cooking", cooking);
			other.put("BREAKFAST", BREAKFAST);
			other.put("FREE_PARKING", FREE_PARKING);
			other.put("CAMPFIRE", CAMPFIRE);
			other.put("PENSIONID", no);
			saveDB_OTHER(other);
			
			
		/** 족구장 존재 여부 */
			String FOOT_VOLLEYBALL_COURT=	el2.select(".theme_57").text().equals("") ? "" : "1";
		/** 노래방 존재 여부 */
			String KARAOKE=	el2.select(".theme_59").text().equals("") ? "" : "1";
		/** 편의점 존재 여부 */
			String CONVENIENCE_STORE=	el2.select(".theme_215").text().equals("") ? "" : "1";
		/** 주차장 존재 여부 */
			String PARKING_LOT=	el2.select(".theme_221").text().equals("") ? "" : "1";
		/** 세미나실 존재 여부 */
			String SEMINAR_ROOM=	el2.select(".theme_58").text().equals("") ? "" : "1";
		/** 바베큐 그릴 존재 여부 */
			String BBQ=	el2.select(".theme_148").text().equals("") ? "" : "1";
		/** 레스토랑 존재 여부 */
			String RESTAURANT=	el2.select(".theme_216").text().equals("") ? "" : "1";
			
		/** TBL_PUBLIC_FACILITIES 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장 */
			Map<String, String> publicFacilities = new HashMap<>();
			publicFacilities.put("FOOT_VOLLEYBALL_COURT",FOOT_VOLLEYBALL_COURT);
			publicFacilities.put("KARAOKE", KARAOKE);
			publicFacilities.put("CONVENIENCE_STORE", CONVENIENCE_STORE);
			publicFacilities.put("PARKING_LOT", PARKING_LOT);
			publicFacilities.put("SEMINAR_ROOM", SEMINAR_ROOM);
			publicFacilities.put("BBQ", BBQ);
			publicFacilities.put("RESTAURANT", RESTAURANT);
			publicFacilities.put("PENSIONID", no);
			saveDB_PUBLIC_FACILITIES(publicFacilities);
			
			
		/** 와이파이 가능 여부 */
			String WIFI= el2.select(".theme_60").text().equals("") ? "" : "1";
		/** TV 존재 여부 */
			String TV=	el2.select(".theme_223").text().equals("") ? "" : "1";
		/** 에어컨 존재 여부 */
			String AIRCONDITIONER=	el2.select(".theme_227").text().equals("") ? "" : "1";
		/** 미니바 존재 여부 */
			String MINIBAR=	el2.select(".theme_226").text().equals("") ? "" : "1";
		/** 욕조 존재 여부 */
			String BATHTUB=	el2.select(".theme_230").text().equals("") ? "" : "1";
		/** 냉장고 존재 여부 */
			String REFRIGERATOR= el2.select(".theme_228").text().equals("") ? "" : "1";
			
		/** TBL_INTERNAL_FACILITIES 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장*/
			Map<String, String> internalFacilities = new HashMap<>();
			internalFacilities.put("WIFI",WIFI);
			internalFacilities.put("TV", TV);
			internalFacilities.put("AIRCONDITIONER", AIRCONDITIONER);
			internalFacilities.put("MINIBAR", MINIBAR);
			internalFacilities.put("BATHTUB", BATHTUB);
			internalFacilities.put("REFRIGERATOR", REFRIGERATOR);
			internalFacilities.put("PENSIONID", no);
			saveDB_INTERNAL_FACILITIES(internalFacilities);
			
			
			System.out.printf(no + "번 작업 완료" +"\n"+"\n");
		}
	}
	
	
	/** TBL_OTHER 테이블에 Map 객체에 저장된 정보를 저장 */ 
	static void saveDB_OTHER(Map<String, String> other) throws Exception{
		java.sql.Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO TBL_OTHER VALUES(?,?,?,?,?,?)");
		pstmt.setString(1, other.get("pickup"));
		pstmt.setString(2, other.get("cooking"));
		pstmt.setString(3, other.get("BREAKFAST"));
		pstmt.setString(4, other.get("FREE_PARKING"));
		pstmt.setString(5, other.get("CAMPFIRE"));
		pstmt.setString(6, other.get("PENSIONID"));
		pstmt.executeUpdate();
		pstmt.close();
	}	
	
	/** TBL_PUBLIC_FACILITIES 테이블에 Map 객체에 저장된 정보를 저장 */ 
	  static void saveDB_PUBLIC_FACILITIES(Map<String, String> publicFacilities) throws Exception{
		java.sql.Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO TBL_PUBLIC_FACILITIES VALUES(?,?,?,?,?,?,?,?)");
		pstmt.setString(1, publicFacilities.get("FOOT_VOLLEYBALL_COURT"));
		pstmt.setString(2, publicFacilities.get("KARAOKE"));
		pstmt.setString(3, publicFacilities.get("CONVENIENCE_STORE"));
		pstmt.setString(4, publicFacilities.get("PARKING_LOT"));
		pstmt.setString(5, publicFacilities.get("SEMINAR_ROOM"));
		pstmt.setString(6, publicFacilities.get("BBQ"));
		pstmt.setString(7, publicFacilities.get("RESTAURANT"));
		pstmt.setString(8, publicFacilities.get("PENSIONID"));
		pstmt.executeUpdate();
		pstmt.close();
	}	
	  
	  /** TBL_INTERNAL_FACILITIES 테이블에 Map 객체에 저장된 정보를 저장 */ 
	  static void saveDB_INTERNAL_FACILITIES(Map<String, String> internalFacilities) throws Exception{
		  java.sql.Connection conn = DBConn.getConnection();
		  PreparedStatement pstmt = conn.prepareStatement(
				  "INSERT INTO TBL_INTERNAL_FACILITIES VALUES(?,?,?,?,?,?,?)");
		  pstmt.setString(1, internalFacilities.get("WIFI"));
		  pstmt.setString(2, internalFacilities.get("TV"));
		  pstmt.setString(3, internalFacilities.get("AIRCONDITIONER"));
		  pstmt.setString(4, internalFacilities.get("MINIBAR"));
		  pstmt.setString(5, internalFacilities.get("BATHTUB"));
		  pstmt.setString(6, internalFacilities.get("REFRIGERATOR"));
		  pstmt.setString(7, internalFacilities.get("PENSIONID"));
		  pstmt.executeUpdate();
		  pstmt.close();
	  }	
}
