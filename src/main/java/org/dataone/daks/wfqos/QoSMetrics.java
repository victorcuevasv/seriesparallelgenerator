package org.dataone.daks.wfqos;


public class QoSMetrics {
	
	
	private double time;
	private double cost;
	private double reliability;
	
	
	public QoSMetrics(double time, double cost, double reliability) {
		this.time = time;
		this.cost = cost;
		this.reliability = reliability;
	}

	
	public void setTime(double time) {
		this.time = time;
	}
	
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	public void setReliability(double reliability) {
		this.reliability = reliability;
	}
	
	
	public double getTime() {
		return this.time;
	}
	
	
	public double getCost() {
		return this.cost;
	}
	
	
	public double getReliability() {
		return this.reliability;
	}
	
	
}


