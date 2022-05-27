package works.yermi.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	public String getParams(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.toUriString();
	}
	public String getParamWithoutPageNum(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("amount", amount)
				.toUriString();
	}
}
