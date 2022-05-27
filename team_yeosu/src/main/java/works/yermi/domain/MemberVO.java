package works.yermi.domain;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberVO {
	private String userId;
	private String pw;
	private String email;
	private String phone;
	
	private Date regDate;
	private Date updateDate;
	private boolean enabled;
	private int point;
	
	private String roadAddr;
	private String addrDetail;
	private String zipNo;
	private String jibunAddr;
	private String name;
	private Date birthDate;
	private String nickName;
	private String phone2;
	private String phone3;
	
	private boolean delStatus; // 회원 탈퇴 쿼리 수정 (delete > update) / id email 정보 제외 null
	private boolean authEmail;
	private String authToken;
	
	private List<AuthVO> auths;
}
