package model;

public class Account {

	public static String ACTIVO = "Activo";
	public static String PASIVO = "Pasivo";
	public static String INGRESO = "Ingreso";
	public static String GASTO = "Gasto";
	public static String CORRIENTE = "Corriente";
	public static String NO_CORRIENTE = "No corriente";
	public static String OPERATIVO = "Operativo";
	public static String NO_OPERATIVO = "No operativo";
	
	
	private String name;
	private long value;
	private String type;
	private String subType;
	private long adjustment;
	private long total;
	
	public Account(String name, long value, String type, String subtType) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.subType = subtType;
	}
	
	public Account(String name, String type, String subtype) {
		this.name = name;
		this.type = type;
		this.subType = subtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", value=" + value + ", type=" + type + "]";
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public long getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(long adjustment) {
		this.adjustment = adjustment;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
}
