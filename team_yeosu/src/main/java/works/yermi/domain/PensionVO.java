package works.yermi.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pension")
public class PensionVO {
	// 기본정보
	private Long pensionid;
	private String name;
	private String userid;
	private int category;
	private boolean enabled;
	private String address;
	private String comments;
	private Long price;
	private Double starRate;
	private int replyCnt;
	private boolean delstatus;
	private Double longitude;
	private Double latitude;
	private boolean adstatus;
	
	// 공용시설
	private boolean footVolleyballCourt;
	private boolean karaoke;
	private boolean convenienceStore;
	private boolean parkingLot;
	private boolean seminarRoom;
	private boolean bbq;
	private boolean restaurant;
	
	// 객실내 시설
	private boolean wifi;
	private boolean tv;
	private boolean airConditioner;
	private boolean miniBar;
	private boolean bathTub;
	private boolean refrigerator;
	
	// 기타
	private boolean pickup;
	private boolean cooking;
	private boolean breakfast;
	private boolean freeParking;
	private boolean campfire;
	
	List<PensionAttachVO> attachs = new ArrayList<>();
}
