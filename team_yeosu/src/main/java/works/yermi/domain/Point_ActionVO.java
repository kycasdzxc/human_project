package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("point_action")
public class Point_ActionVO {
	private int pointNum;
	private int action;
	private int point;
}
