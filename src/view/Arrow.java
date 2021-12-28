package view;

import java.awt.Color;
import java.util.ArrayList;

import models.LinearFunction;

public class Arrow {
	
	private double x1, x2, y1, y2;
	private LinearFunction awn1;
	private LinearFunction awn2;
	private ArrayList<GraphicSymbol> graphicSymbols;
	
	public Arrow() {
		graphicSymbols = new ArrayList<GraphicSymbol>();
	}
	
	public Arrow(double x1, double y1, double x2, double y2, LinearFunction awn1, LinearFunction awn2, Color color) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.awn1 = awn1;
		this.awn2 = awn2;
		graphicSymbols = new ArrayList<GraphicSymbol>();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

	
	
	

	public double getAwn1XFromY(double y) {
		return awn1.getXFromY(y);
	}
	
	public double getAwn1YFromX(double x) {
		return awn1.getYFromX(x);
	}
	
	public double getAwn2XFromY(double y) {
		return awn2.getXFromY(y);
	}
	
	public double getAwn2YFromX(double x) {
		return awn2.getYFromX(x);
	}

	public double getX1() {
		return x1;
	}


	public void setX1(double x1) {
		this.x1 = x1;
	}


	public double getX2() {
		return x2;
	}


	public void setX2(double x2) {
		this.x2 = x2;
	}


	public double getY1() {
		return y1;
	}


	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY2() {
		return y2;
	}


	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	public LinearFunction getAwn1() {
		return awn1;
	}
	
	public LinearFunction getAwn2() {
		return awn2;
	}
	
	public void setAwn1(LinearFunction awn1) {
		this.awn1 = awn1;
	}
	
	public void setAwn2(LinearFunction awn2) {
		this.awn2 = awn2;
	}

	public void appendSymbol(GraphicSymbol symbol) {
		graphicSymbols.add(symbol);
	}
	
	public void updateGraphicSymbolsPosition() {
		double y = (y2 + y1) / 2;
		double x = (x2 + x1) / 2;
		double pileUpCounter = 1;
		for (GraphicSymbol graphicSymbol : graphicSymbols) {
			graphicSymbol.setX((int)x);
			graphicSymbol.setY((int)(y - (10*pileUpCounter)));
			pileUpCounter++;
		}
	}

}
