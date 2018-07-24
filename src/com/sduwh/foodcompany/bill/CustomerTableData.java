package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.comm.MD5;

public class CustomerTableData {
	String cusName, cusPsw, cusTel, cusAddress;
	int cusRank;
	float cusScore;
	
	public CustomerTableData(String cusName, String cusPsw, String cusTel, String cusAddress, int cusRank, float cusScore) {
		this.cusName = cusName;
		this.cusPsw = MD5.getMD5(cusPsw);
		this.cusTel = cusTel;
		this.cusAddress = cusAddress;
		this.cusRank = cusRank;
		this.cusScore = cusScore;
	}
	
	public String getCusName() {
		return this.cusName;
	}
	public String getCusPsw() {
		return this.cusPsw;
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
	
}
