package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Member {
	private String id;
	private String pw;
	private String name;
	
	private String si;
	private String sgg;
	private String emd;
	private String roadAddr;
	private String addrDetail;
	private String zipNo;
	private String roadFullAddr;
	private String jibunAddr;
	
	private String email;
	
	private String auth;
	private String authToken;

	private String speak;
	
	private Attach attach;
	
	public Member(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
}