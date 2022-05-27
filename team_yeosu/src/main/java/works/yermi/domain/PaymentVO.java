package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("payment")
public class PaymentVO {
	private Long paymentNum;
	private Integer paymentPrice;
	private String paymentDate;
	private boolean paymentStatus;
	private String userid;
	private Long pensionid;
	private Long roomNum;
	
	private String name;
	private String roomName;
	
	public PaymentVO(Long paymentNum, Integer paymentPrice, String paymentDate, boolean paymentStatus, String userid,
			Long pensionid, Long roomNum) {
		this.paymentNum = paymentNum;
		this.paymentPrice = paymentPrice;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.userid = userid;
		this.pensionid = pensionid;
		this.roomNum = roomNum;
	}
}
