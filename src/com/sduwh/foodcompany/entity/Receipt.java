package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Receipt表，实现toString，实现equals
 */
import java.util.Date;
public class Receipt implements Serializable{
	private String receipt_id;
	private String order_id;
	private float receipt_money;
	private String teller_user_id;
	private Date receipt_date;
	//默认构造函数
	public Receipt() {
		receipt_id = "";
		order_id = "";
		receipt_money = 0;	
		teller_user_id = "";
		receipt_date = null;
	}
	public String getReceipt_id() {
		return receipt_id;
	}
	public void setReceipt_id(String receipt_id) {
		this.receipt_id = receipt_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public float getReceipt_money() {
		return receipt_money;
	}
	public void setReceipt_money(float receipt_money) {
		this.receipt_money = receipt_money;
	}
	public String getTeller_user_id() {
		return teller_user_id;
	}
	public void setTeller_user_id(String teller_user_id) {
		this.teller_user_id = teller_user_id;
	}
	public Date getReceipt_date() {
		return receipt_date;
	}
	public void setReceipt_date(Date receipt_date) {
		this.receipt_date = receipt_date;
	}
	@Override
	public String toString() {
		return "Receipt [receipt_id=" + receipt_id + ", order_id=" + order_id + ", receipt_money=" + receipt_money
				+ ", teller_user_id=" + teller_user_id + ", receipt_date=" + receipt_date + "]";
	}
	public boolean equals(Receipt c) {
		if(c instanceof Receipt) {
			String id = c.getReceipt_id();
			if(id.equals(getReceipt_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
