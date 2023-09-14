package com.eassets.model.util;

public class AssetTypeProperties {
	private int lendingPeriod;
    private double lateReturnFee;
    private int daysToBan;
    
	public AssetTypeProperties(int lendingPeriod, double lateReturnFee, int daysToBan) {
		super();
		this.lendingPeriod = lendingPeriod;
		this.lateReturnFee = lateReturnFee;
		this.daysToBan = daysToBan;
	}
	
	public int getLendingPeriod() {
		return lendingPeriod;
	}
	public void setLendingPeriod(int lendingPeriod) {
		this.lendingPeriod = lendingPeriod;
	}
	public double getLateReturnFee() {
		return lateReturnFee;
	}
	public void setLateReturnFee(double lateReturnFee) {
		this.lateReturnFee = lateReturnFee;
	}
	public int getDaysToBan() {
		return daysToBan;
	}
	public void setDaysToBan(int daysToBan) {
		this.daysToBan = daysToBan;
	}
}
