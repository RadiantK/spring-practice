package exam.test12;

import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;

public class Tire {
	private String maker; // 제조사
	private Properties spec; // 규격
	private Date createdDate;

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Properties getSpec() {
		return spec;
	}

	public void setSpec(Properties spec) {
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
		StringBuilder specInfo = new StringBuilder();
		if(spec != null) {
			for(Entry<Object, Object> entry : spec.entrySet()){
				specInfo.append(entry.getKey() + ":" + entry.getValue()+",");
			}
		}
		return "[Tire:" + maker + "," + specInfo.toString() + 
				((createdDate != null) ? 
						(","+createdDate.toString()) : "") + "]";
	}
	
}
