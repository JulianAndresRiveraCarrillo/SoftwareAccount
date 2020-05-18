package model;

public class Patrimony {

	String patrimony;
	long value;

	public Patrimony(String patrimony, long value) {
		this.patrimony = patrimony;
		this.value = value;
	}

	public String getPatrimony() {
		return patrimony;
	}

	public void setPatrimony(String patrimony) {
		this.patrimony = patrimony;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
