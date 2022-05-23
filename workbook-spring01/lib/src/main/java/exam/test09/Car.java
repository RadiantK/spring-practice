package exam.test09;

public class Car {
	private String model; // 모델명
	private Engine engine; // 엔진
	private Tire[] tires;
	
	public Car() {}

	public Car(String model, Engine engine) {
		this.model = model;
		this.engine = engine;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Tire[] getTires() {
		return tires;
	}

	public void setTires(Tire[] tires) {
		this.tires = tires;
	}
	
	@Override
	public String toString() {
		StringBuilder carInfo = new StringBuilder();
		carInfo.append("[Car:" + model);
		carInfo.append("\n " + engine.toString());
		
		if(tires != null) {
			for(Tire tire : tires) {
				carInfo.append("\n " + tire.toString());
			}
		}
		
		carInfo.append("\n]");
		return carInfo.toString();
	}
}
