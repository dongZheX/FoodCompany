package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的PickUp表，实现toString，实现equals
 */
public class PickUp implements Serializable{
	private String pick_up_id ;
	private int pick_up_state;
	private String accountant_user_id;
	//默认构造函数
	public PickUp() {
		pick_up_state = 0;
		pick_up_id = "";
		accountant_user_id = "";
	}
	//setters and getters
	public String getPick_up_id() {
		return pick_up_id;
	}
	public void setPick_up_id(String pick_up_id) {
		this.pick_up_id = pick_up_id;
	}
	public int getPick_up_state() {
		return pick_up_state;
	}
	public void setPick_up_state(int pick_up_state) {
		this.pick_up_state = pick_up_state;
	}
	public String getAccountant_user_id() {
		return accountant_user_id;
	}
	public void setAccountant_user_id(String accountant_user_id) {
		this.accountant_user_id = accountant_user_id;
	}
	@Override
	public String toString() {
		return "PickUp [pick_up_id=" + pick_up_id + ", pick_up_state=" + pick_up_state + ", accountant_user_id="
				+ accountant_user_id + "]";
	}
	public boolean equals(PickUp c) {
		if(c instanceof PickUp) {
			String id = c.getPick_up_id();
			if(id.equals(getPick_up_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
