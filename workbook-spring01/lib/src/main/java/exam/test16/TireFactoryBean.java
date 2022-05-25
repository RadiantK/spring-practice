package exam.test16;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class TireFactoryBean extends AbstractFactoryBean<Tire> {
	private String maker;
	
	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	@Override
	public Class<?> getObjectType() {
		return exam.test13.Tire.class;
	}
	
	protected Tire createInstance() {
		if(maker.equals("Hankook")) {
			return createHanKookTire();
		}else {
			return createKumhoTire();
		}
	}

	private Tire createHanKookTire() {
		Tire tire = new Tire();
		tire.setMaker("Hankook");
		
		Properties spec = new Properties();
		spec.setProperty("width", "205");
		spec.setProperty("ratio", "65");
		spec.setProperty("rim.diameter", "14");
		tire.setSpec(spec);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			tire.setCreatedDate(dateFormat.parse("2020-05-05"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tire;
	}
	
	private Tire createKumhoTire() {
		Tire tire = new Tire();
		tire.setMaker("Kumho");
		
		Properties spec = new Properties();
		spec.setProperty("width", "185");
		spec.setProperty("ratio", "75");
		spec.setProperty("rim.diameter", "16");
		tire.setSpec(spec);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			tire.setCreatedDate(dateFormat.parse("2020-04-05"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tire;
	}

}