package com.sduwh.foodcompany.entity;

import java.sql.Date;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Returned表，实现toString，实现equals
 */
public class Returned {
	private String returned_id;
	private String return_id;
	private String teller_user_id;
	private Date returned_date;
	//默认构造函数
	public Returned() {
		return_id = "";
		return_id = "";
		teller_user_id = "";
				returned_date = null;
	}
	//setters and getters
	public String getReturned_id() {
		return returned_id;
	}
	public void setReturned_id(String returned_id) {
		this.returned_id = returned_id;
	}
	public String getReturn_id() {
		return return_id;
	}
	public void setReturn_id(String return_id) {
		this.return_id = return_id;
	}
	public String getTeller_user_id() {
		return teller_user_id;
	}
	public void setTeller_user_id(String teller_user_id) {
		this.teller_user_id = teller_user_id;
	}
	public Date getReturned_date() {
		return returned_date;
	}
	public void setReturned_date(Date returned_date) {
		this.returned_date = returned_date;
	}
	@Override
	public String toString() {
		return "Returned [returned_id=" + returned_id + ", return_id=" + return_id + ", teller_user_id="
				+ teller_user_id + ", returned_date=" + returned_date + "]";
	}
	public boolean equals(Returned c) {
		if(c instanceof Returned) {
			String id = c.getReturned_id();
			if(id.equals(c.getReturned_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
