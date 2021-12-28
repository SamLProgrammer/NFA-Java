package models;

import view.Arrow;
import view.JPanelAutomate;

public class MutantCircle {
	
	private double xCenter;
	private double yCenter;
	private double radius;
	
	public MutantCircle(double xCenter, double yCenter, double radius) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.radius = radius;
	}
	
	public double generateAboveYPoint(double x) {		
		return Math.sqrt((radius*radius) - (x-xCenter)*(x-xCenter)) + yCenter;
	}
	
	public double generateBottomYPoint(double x) {		
		return Math.sqrt((radius*radius) - (x-xCenter)*(x-xCenter))*-1 + yCenter;
	}
	
	public double intersectLine(Arrow arrow) {
		LinearFunction line = new LinearFunction(arrow);
		double x1 = arrow.getX1();
		double y1 = arrow.getY1();
		double b = line.getB();
		double m = line.getM();
		double root1 = 0;
		double root2 = 0;
		double d = b-yCenter;
		double e = radius*radius - xCenter*xCenter;
		double f = m*m + 1;
		double g = 2*m*d - 2*xCenter;
		double right_factor = (d*d - e)*4*f;
		double sqrd_factor = Math.sqrt(g*g - right_factor);
		root1 = (-1*g - sqrd_factor) / (2*f);
		root2 = (-1*g + sqrd_factor) / (2*f);
		double root1_distance_sqrd = (x1-root1)*(x1-root1) + (y1-line.getYFromX(root1))*(y1-line.getYFromX(root1));
		double root2_distance_sqrd = (x1-root2)*(x1-root2) + (y1-line.getYFromX(root2))*(y1-line.getYFromX(root2));
		if(root1_distance_sqrd < root2_distance_sqrd) {
			return root1;
		} else {
			return root2;
		}
	}
	
	public double intersectAwn1(Arrow arrow) {
		double x1 = arrow.getX1();
		double y1 = arrow.getY1();
		double b = arrow.getAwn1().getB();
		double m = arrow.getAwn1().getM();
		double d = b-yCenter;
		double e = radius*radius - xCenter*xCenter;
		double f = m*m + 1;
		double g = 2*m*d - 2*xCenter;
		double right_factor = (d*d - e)*4*f;
		double sqrd_factor = Math.sqrt(g*g - right_factor);
		double root1 = (-1*g - sqrd_factor) / (2*f);
		double root2 = (-1*g + sqrd_factor) / (2*f); 
		double root1_distance_sqrd = (x1-root1)*(x1-root1) + (y1-arrow.getAwn1YFromX((int)root1))*(y1-arrow.getAwn1YFromX((int)root1));
		double root2_distance_sqrd = (x1-root2)*(x1-root2) + (y1-arrow.getAwn1YFromX((int)root2))*(y1-arrow.getAwn1YFromX((int)root2));
		if(root1_distance_sqrd < root2_distance_sqrd) {
			return root1;
		} else {
			return root2;
		}
	}
	
	public double intersectAwn2(Arrow arrow) {
		double x1 = arrow.getX1();
		double y1 = arrow.getY1();
		double b = arrow.getAwn2().getB();
		double m = arrow.getAwn2().getM();
		double d = b-yCenter;
		double e = radius*radius - xCenter*xCenter;
		double f = m*m + 1;
		double g = 2*m*d - 2*xCenter;
		double right_factor = (d*d - e)*4*f;
		double sqrd_factor = Math.sqrt(g*g - right_factor);
		double root1 = (-1*g - sqrd_factor) / (2*f);
		double root2 = (-1*g + sqrd_factor) / (2*f); 
		double root1_distance_sqrd = (x1-root1)*(x1-root1) + (y1-arrow.getAwn2YFromX((int)root1))*(y1-arrow.getAwn2YFromX((int)root1));
		double root2_distance_sqrd = (x1-root2)*(x1-root2) + (y1-arrow.getAwn2YFromX((int)root2))*(y1-arrow.getAwn2YFromX((int)root2));
		if(root1_distance_sqrd < root2_distance_sqrd) {
			return root1;
		} else {
			return root2;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public double getxCenter() {
		return xCenter;
	}

	public void setxCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public double getyCenter() {
		return yCenter;
	}

	public void setyCenter(double yCenter) {
		this.yCenter = yCenter;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}
