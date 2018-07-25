package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.comm.MD5;

public class CustomerAlterData {
	String cusName, cusId, cusTel, cusAddress;
	int cusRank;
	float cusScore;
	
	public CustomerAlterData(String cusId, String cusName, String cusTel, String cusAddress, int cusRank, float cusScore) {
		//"客户编号","客户姓名","联系方式","客户等级","信誉积分","客户地址"
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusTel = cusTel;
		this.cusAddress = cusAddress;
		this.cusRank = cusRank;
		this.cusScore = cusScore;
	}
	
	public String getCusName() {
		return this.cusName;
	}
	public String getId() {
		return this.cusId;
	}
	public String getCusTel() {
		return this.cusTel;
	}
	public String getCusAddress() {
		return this.cusAddress;
	}
	public int getCusRank() {
		return this.cusRank;
	}
	public float getCusScore() {
		return this.cusScore;
	}
	
	public String[] getArray() {
		String[] s = {
				this.cusId,this.cusName, this.cusTel,  this.cusAddress, String.format("%d", this.cusRank), String.format("%.2f", this.cusScore)
		};
		return s;
	}
}
