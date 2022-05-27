package works.yermi.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("point_history")
public class Point_HistoryVO {
	private String userId;
	private Date addPointDate;
	private int addPoint;
	private int pointNum;
	private String pointHistoryNum;
}
