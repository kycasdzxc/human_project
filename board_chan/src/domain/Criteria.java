package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO
@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	private int category = 1;
	
	public String getParams2() {
		return getParams() + "&pageNum=" + pageNum;
	}
	public String getParams() {
		return "?amount=" + amount +
				"&category=" + category;
	}
	
	public String getParamMember() {
		return "?amount=" + amount + "&pageNum=" + pageNum;
	}
}