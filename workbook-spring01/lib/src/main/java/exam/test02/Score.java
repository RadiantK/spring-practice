package exam.test02;

public class Score {
	private String name;
	private double kor;
	private double eng;
	private double math;
	
	public Score() { }
	
	public Score(String name, double kor, double eng, double math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public double average() {
		return sum() / (float)3;
	}
	
	public double sum() {
		return kor + eng + math;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getKor() {
		return kor;
	}
	
	public void setKor(float kor) {
		this.kor = kor;
	}

	public double getEng() {
		return eng;
	}

	public void setEng(float eng) {
		this.eng = eng;
	}

	public double getMath() {
		return math;
	}

	public void setMath(float math) {
		this.math = math;
	}

}
