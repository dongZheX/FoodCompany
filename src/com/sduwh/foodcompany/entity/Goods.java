package com.sduwh.foodcompany.entity;

import java.io.Serializable;
import java.util.Date;

/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Goods表，实现toString，实现equals
 */
public class Goods implements Serializable{
	private String good_id;
	private String good_name;
	private String good_standard;
	private float good_cost;
	private Date good_expiration_date;
	//默认构造函数
	public Goods(){
		good_id = "";
		good_name = "";
		good_standard = "";
		good_cost = 0;
		
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getGood_standard() {
		return good_standard;
	}
	public void setGood_standard(String good_standard) {
		this.good_standard = good_standard;
	}
	public float getGood_cost() {
		return good_cost;
	}
	public void setGood_cost(float good_cost) {
		this.good_cost = good_cost;
	}
	public Date getGood_expiration_date() {
		return good_expiration_date;
	}
	public void setGood_expiration_date(Date good_expiration_date) {
		this.good_expiration_date = good_expiration_date;
	}
	@Override
	public String toString() {
		return "Goods [good_id=" + good_id + ", good_name=" + good_name + ", good_standard=" + good_standard
				+ ", good_cost=" + good_cost + ", good_expiration_date=" + good_expiration_date + "]";
	}
	public boolean equals(Goods c) {
		if(c instanceof Goods) {
			String id = c.getGood_id();
			if(id.equals(getGood_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
