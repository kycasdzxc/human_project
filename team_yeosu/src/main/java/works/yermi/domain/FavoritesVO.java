package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("favorite")
public class FavoritesVO {
	private String regDate;
	private String userid;
	private String pensiond;
	private int category;
}
