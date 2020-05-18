package model;

import java.util.ArrayList;

public class BalanceSheet extends Data {

	ArrayList<Account> activosC;
	ArrayList<Account> activosNC;
	ArrayList<Account> pasivosC;
	ArrayList<Account> pasivosNC;
	ArrayList<Account> patrimonio;
	int totalActivosC;
	int totalActivosNC;
	int totalPasivosC;
	int totalPasivosNC;
	int totalActivos;
	int totalPasivos;
	int totalPatrimonio;
	int totalPatrimonioyPasivo;

	public BalanceSheet(ArrayList<Account> activosC, ArrayList<Account> activosNC, ArrayList<Account> pasivosC,
			ArrayList<Account> pasivosNC, ArrayList<Account> patrimonio, int totalActivosC, int totalActivosNC,
			int totalPasivosC, int totalPasivosNC, int totalActivos, int totalPasivos, int totalPatrimonio,
			int totalPatrimonioyPasivo) {
		this.activosC = activosC;
		this.activosNC = activosNC;
		this.pasivosC = pasivosC;
		this.pasivosNC = pasivosNC;
		this.patrimonio = patrimonio;
		this.totalActivosC = totalActivosC;
		this.totalActivosNC = totalActivosNC;
		this.totalPasivosC = totalPasivosC;
		this.totalPasivosNC = totalPasivosNC;
		this.totalActivos = totalActivos;
		this.totalPasivos = totalPasivos;
		this.totalPatrimonio = totalPatrimonio;
		this.totalPatrimonioyPasivo = totalPatrimonioyPasivo;
	}

	public ArrayList<Account> getActivosC() {
		return activosC;
	}

	public void setActivosC(ArrayList<Account> activosC) {
		this.activosC = activosC;
	}

	public ArrayList<Account> getActivosNC() {
		return activosNC;
	}

	public void setActivosNC(ArrayList<Account> activosNC) {
		this.activosNC = activosNC;
	}

	public ArrayList<Account> getPasivosC() {
		return pasivosC;
	}

	public void setPasivosC(ArrayList<Account> pasivosC) {
		this.pasivosC = pasivosC;
	}

	public ArrayList<Account> getPasivosNC() {
		return pasivosNC;
	}

	public void setPasivosNC(ArrayList<Account> pasivosNC) {
		this.pasivosNC = pasivosNC;
	}

	public ArrayList<Account> getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(ArrayList<Account> patrimonio) {
		this.patrimonio = patrimonio;
	}

	public int getTotalActivosC() {
		return totalActivosC;
	}

	public void setTotalActivosC(int totalActivosC) {
		this.totalActivosC = totalActivosC;
	}

	public int getTotalActivosNC() {
		return totalActivosNC;
	}

	public void setTotalActivosNC(int totalActivosNC) {
		this.totalActivosNC = totalActivosNC;
	}

	public int getTotalPasivosC() {
		return totalPasivosC;
	}

	public void setTotalPasivosC(int totalPasivosC) {
		this.totalPasivosC = totalPasivosC;
	}

	public int getTotalPasivosNC() {
		return totalPasivosNC;
	}

	public void setTotalPasivosNC(int totalPasivosNC) {
		this.totalPasivosNC = totalPasivosNC;
	}

	public int getTotalActivos() {
		return totalActivos;
	}

	public void setTotalActivos(int totalActivos) {
		this.totalActivos = totalActivos;
	}

	public int getTotalPasivos() {
		return totalPasivos;
	}

	public void setTotalPasivos(int totalPasivos) {
		this.totalPasivos = totalPasivos;
	}

	public int getTotalPatrimonio() {
		return totalPatrimonio;
	}

	public void setTotalPatrimonio(int totalPatrimonio) {
		this.totalPatrimonio = totalPatrimonio;
	}

	public int getTotalPatrimonioyPasivo() {
		return totalPatrimonioyPasivo;
	}

	public void setTotalPatrimonioyPasivo(int totalPatrimonioyPasivo) {
		this.totalPatrimonioyPasivo = totalPatrimonioyPasivo;
	}

}
