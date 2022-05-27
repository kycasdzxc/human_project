package works.yermi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AttachFileDTO {
	private String uuid;
	private String origin;
	private String path;
	private int ord;
	private boolean image;
	
}
