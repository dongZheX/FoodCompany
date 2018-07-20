package com.sduwh.foodcompany.entity;

import java.awt.datatransfer.FlavorTable;
import java.util.Date;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Returning表，实现toString，实现equals
 */
public class Returning {
	private String return_id;
	private float return_money;
	private String sale_user_id;
	private Date return_date;
	//默认构造函数
	public Returning(){
		return_id = "";
		return_money = 0;
		return_date =  null;
		sale_user_id = "";	
	}
	//getters and setters 
	public String getReturn_id() {
		return return_id;
	}
	public void setReturn_id(String return_id) {
		this.return_id = return_id;
	}
	public float getReturn_money() {
		return return_money;
	}
	public void setReturn_money(float return_money) {
		this.return_money = return_money;
	}
	public String getSale_user_id() {
		return sale_user_id;
	}
	public void setSale_user_id(String sale_user_id) {
		this.sale_user_id = sale_user_id;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	@Override
	public String toString() {
		return "Returning [return_id=" + return_id + ", return_money=" + return_money + ", sale_user_id=" + sale_user_id
				+ ", return_date=" + return_date + "]";
	}
	public boolean equals(Returning c) {
		if(c instanceof Returning) {
			String id = c.getReturn_id();
			if(id.equals(c.getReturn_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
