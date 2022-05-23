package exam.test09;

import java.util.Date;

public class Tire {
	private String maker; // 제조사
	private String spec; // 규격
	private Date createdDate;

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Tire [maker=" + maker + ", spec=" + spec + ", createdDate=" + createdDate + "]";
	}
	
}
