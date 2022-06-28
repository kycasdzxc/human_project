package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.DBConn;

public class HTMLParserPension {
    public static void main(String[] args) throws Exception {
    	/** 여기어때 펜션 카테고리(1) 페이지 연결	*/
		Connection conn = Jsoup.connect("https://www.goodchoice.kr/product/search/3/3108?sort=HIT&sel_date=2022-06-20&sel_date2=2022-06-21&grade%5B%5D=PENSION&persons=&min_price=&max_price=");
		/** 여기어때 풀빌라 카테고리(2) 페이지 연결	*/
	//	Connection conn = Jsoup.connect("https://www.goodchoice.kr/product/search/3/3108?sort=HIT&sel_date=2022-06-20&sel_date2=2022-06-21&grade%5B%5D=POOLVILLA&persons=&min_price=&max_price=");
		
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
			
		/** 각각의 펜션 이름을 title 이라는 String 타입 변수에 저장 */
			String title = doc2.select(".info").select("h2").text();
			
		/** 각각의 펜션 가격을 price 라는 String 타입 변수에 저장 */ 
			String price= el.select(".map_html").select("b").html();
			
		/** 각각의 펜션 주소를 address 라는 String 타입 변수에 저장 */ 	
			String address = doc2.select(".address").text();
		
		/** 펜션별로 존재하는 사장님 한마디를 comment 라는 String 타입 변수에 저장 */
			String comment = doc2.select(".comment div").text();
			
		/** TBL_PENSION 테이블에 정보를 저장하기 위한 Map 객체 생성 후 put을 이용한 저장 */
			Map<String, String> map = new HashMap<>();
			map.put("PENSIONID",no);
			map.put("NAME", title);
			map.put("address", address);
			map.put("comment", comment);
			map.put("price", price);
			saveDB(map);
			
		/** 각각의 펜션 이미지들을 지정할 Elements 객체 생성 */
			Elements el3 = doc2.select(".swiper-slide");
			
		/** 펜션별 이미지들을 String 배열로 저장하기 위함 */
			List<String> imgs = new ArrayList<>();
			
		/** 이미지 파일의 누락 및 손상을 확인하기 위함 */	
			boolean check = true;
		
		/** 펜션별 이미지들을 한 개씩 지정할 Element 객체 생성 */
			for (int j = 0; j < el3.size()-1; j++) {
				Element eximg= el3.select(".swiper-lazy").get(j);
				
		/** 펜션별 이미지들을 링크 형태로 img 라는 String 타입 변수에 저장 */
				String img ="https:"+ eximg.attr("data-src");
		
		/** 이미지 파일의 누락 및 손상을 확인하여 배열에 추가 */
				if(imgs.size() == 0) {
					imgs.add(img);
				}
				else {
					for(String s : imgs) {
						if(!img.equals(s)) {
							imgs.add(img);
							break;
						}
						else {
							check = false;
						}
					}
				}
				if(!check) {
					break;
				}
				saveFile(no, img,j);
			}
			
			
			System.out.printf(no + "번 작업 완료" +"\n"+"\n");
        }
    }
    
    /** 이미지 파일들을 링크를 지정하여 저장, TBL_PENSION_ATTACH 테이블에 정보를 저장 */ 
    static void saveFile(String no, String imgSrc, int ORD) throws Exception {
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
		        path= "main\\" + no;
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
          
        java.sql.Connection conn = DBConn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
      			"INSERT INTO TBL_PENSION_ATTACH VALUES(?,?,?,1,?,?)");
    /** 이미지 파일 고유번호 */
      	pstmt.setString(1,name);
  	/** 이미지 파일 원본이름 */
      	pstmt.setString(2,name);
  	/** 이미지 파일 경로 */
      	pstmt.setString(3, path);
  	/** 이미지 파일 순서 */
      	pstmt.setInt(4, ORD);
  	/** 펜션 고유번호 */
      	pstmt.setString(5, no);
      	pstmt.executeUpdate();
      	pstmt.close();
    }
    
	/** TBL_PENSION 테이블에 Map 객체에 저장된 정보를 저장 */ 
    static void saveDB(Map<String, String> map) throws Exception{
    	java.sql.Connection conn = DBConn.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement(
    			"INSERT INTO TBL_PENSION VALUES(?,?,NULL,1,1,?,?,?)");
    /** 풀빌라 카테고리(2)의 경우 */	
//    			"INSERT INTO TBL_PENSION VALUES(?,?,NULL,2,1,?,?,?)");
    	pstmt.setString(1, map.get("PENSIONID"));
    	pstmt.setString(2, map.get("NAME"));
    	pstmt.setString(3, map.get("address"));
    	pstmt.setString(4, map.get("comment"));
    	pstmt.setString(5, map.get("price"));
    	pstmt.executeUpdate();
    	pstmt.close();
    }
}

