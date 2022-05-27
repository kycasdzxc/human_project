package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data @Alias("reservation")
public class ReservationVO {
	private Long reservationNum;
	private String checkin;
	private String checkout;
	private Integer paymentPrice;
	private boolean reservationStatus;
	private Long roomNum;
	private String userid;
	private Long pensionid;
	
	private String name;
	private String roomName;
	
	public ReservationVO(Long reservationNum, String checkin, String checkout, Integer paymentPrice,
			boolean reservationStatus, Long roomNum, String userid, Long pensionid) {
		this.reservationNum = reservationNum;
		this.checkin = checkin;
		this.checkout = checkout;
		this.paymentPrice = paymentPrice;
		this.reservationStatus = reservationStatus;
		this.roomNum = roomNum;
		this.userid = userid;
		this.pensionid = pensionid;
	}
}
