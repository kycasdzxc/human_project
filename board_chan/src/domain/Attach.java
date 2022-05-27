package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Attach {
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
	private int ord = 1;
	private String id;
	
	public Attach(String uuid, String origin, String path) {
		this.uuid = uuid;
		this.origin = origin;
		this.path = path;
	}

	public String getParams() {
		return "?uuid=" + uuid + "&path=" + path + "&origin=" + origin;
	}
}