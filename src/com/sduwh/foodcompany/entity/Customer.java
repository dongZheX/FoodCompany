package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/21
 * 映射数据库中的Customer表，继承自User表，实现toString，实现equals
 */
public class Customer extends User implements Serializable{
	//一级批发商
	public final static int FIRST_GRADE_WHOLESALE = 1;
	//二级批发商
	public final static int SECOND_GRADE_WHOLESALE = 2;
	//零售
	public final static int RETAIL_GRADE_SALE = 3;
	
	private int cus_rank;
	private float cus_score;
	private String cus_address;
	//默认构造方法
	public Customer(){
		user_id = "";
		user_name = "";
		user_psw = "";
		user_tel = "";
		cus_rank = 0;
		cus_score = 0;
		cus_address = "";
	}
	//getter and setters
	public int getCus_rank() {
		return cus_rank;
		
	}
	public void setCus_rank(int cus_rank) {
		this.cus_rank = cus_rank;
	}
	public float getCus_score() {
		return cus_score;
	}
	public void setCus_score(float cus_score) {
		this.cus_score = cus_score;
	}
	public String getCus_address() {
		return cus_address;
	}
	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;		
	}
	@Override
	public String toString() {
		return "Customer [cus_rank=" + cus_rank + ", cus_score=" + cus_score + ", cus_address=" + cus_address
				+ ", user_id=" + user_id + ", user_name=" + user_name + ", user_psw=" + user_psw + ", user_tel="
				+ user_tel + "]";
	}
	public boolean equals(Customer c) {
		if(c instanceof Customer) {
			String id = c.getUser_id();
			if(id.equals(getUser_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
	
}
