package com.sduwh.foodcompany.entity;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Bill表，实现toString，实现equals
 */

import java.util.Date;

public class Bill {
	private String bill_id;
	private Date bill_date;
	private float bill_money;
	private String receipt_id;//收货id 退货id
	private String acountant_user_id;
	//默认构造函数
	public Bill(){
		bill_id = "";
		bill_date = null;
		bill_money =0;
		receipt_id = "";
		acountant_user_id = "";
	}
	//getters and setters
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public float getBill_money() {
		return bill_money;
	}
	public void setBill_money(float bill_money) {
		this.bill_money = bill_money;
	}
	public String getReceipt_id() {
		return receipt_id;
	}
	public void setReceipt_id(String receipt_id) {
		this.receipt_id = receipt_id;
	}
	public String getAcountant_user_id() {
		return acountant_user_id;
	}
	public void setAcountant_user_id(String acountant_user_id) {
		this.acountant_user_id = acountant_user_id;
	}
	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", bill_date=" + bill_date + ", bill_money=" + bill_money + ", receipt_id="
				+ receipt_id + ", acountant_user_id=" + acountant_user_id + "]";
	}
	public boolean equals(Bill c) {
		if(c instanceof Bill) {
			String id = c.getBill_id();
			if(id.equals(getBill_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
	
}
