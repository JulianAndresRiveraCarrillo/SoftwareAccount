package model;

import javafx.scene.image.Image;

public class Data {

	protected String companyName;
	protected String NIT;
	protected String initialPeriod;
	protected String finalPeriod;
	private Image image;
	
	
	public Data(String companyName, String nIT, String initialPeriod, String finalPeriod, Image img) {
		this.companyName = companyName;
		NIT = nIT;
		this.initialPeriod = initialPeriod;
		this.finalPeriod = finalPeriod;
		setImage(img);
	}
	
	public Data() {
		
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	public String getInitialPeriod() {
		return initialPeriod;
	}

	public void setInitialPeriod(String initialPeriod) {
		this.initialPeriod = initialPeriod;
	}

	public String getFinalPeriod() {
		return finalPeriod;
	}

	public void setFinalPeriod(String finalPeriod) {
		this.finalPeriod = finalPeriod;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
