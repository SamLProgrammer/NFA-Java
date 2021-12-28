package models;

public class Production {
	private String father;
	private String production;
	
	public Production(String father, String production) {
		this.father = father;
		this.production = production;
	}
	
	
	public String toString() {
		return father + "->" + production;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setFather(String father) {
		this.father = father;
	}
	
	public String getFather() {
		return father;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getProduction() {
		return production;
	}
	
}
