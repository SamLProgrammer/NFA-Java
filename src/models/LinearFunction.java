package models;

import view.Arrow;

public class LinearFunction {

	private double m;
	private double b;
	private double denominator;
	
	public LinearFunction(double m, double b) {
		this.m = m;
		this.b = b;
	}
	
	public LinearFunction(Arrow arrow) {
		denominator = (double) (arrow.getX2() - arrow.getX1());
		double m = (double) (arrow.getY2() - arrow.getY1()) / (double) (arrow.getX2() - arrow.getX1());
		double b = arrow.getY2() - (m*arrow.getX2());
		this.m = m;
		this.b = b;
	}
	
	public double getXFromY(double y) {
		return (y-b)/m;
	}
	
	public double getYFromX(double x) {
		return m*x + b;
	}
	
	@Override
	public String toString() {
		return "y = " + m +"x " + "+ " + b;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public double getDenominator() {
		return denominator;
	}
	
	public double getM() {
		return m;
	}
	
	public double getB() {
		return b;
	}
	
}
