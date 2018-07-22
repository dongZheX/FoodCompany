package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/21
 * 映射数据库中的PickUp表，实现toString，实现equals
 */
import java.util.ArrayList;
public class PickUp implements Serializable{
	//未提货
	public static final int HAVE_NOT_PICKUP = 1;
	//已提货
	public static final int HAVE_PICKUP = 2;
	//退货销毁
	public static final int HAVE_DESTORYED_PICKUP = 3;
	
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
	
	//数字转文字
	public static String state_toString(PickUp pickUp){
		String pick_up_state_str = "";
		switch (pickUp.getPick_up_state()) {
		case PickUp.HAVE_NOT_PICKUP:
			pick_up_state_str = "未提货";
			break;
		case PickUp.HAVE_PICKUP:
			pick_up_state_str = "已提货";
			break;
		case PickUp.HAVE_DESTORYED_PICKUP:
			pick_up_state_str = "退货销毁";
			break;
		default:pick_up_state_str = null;
			break;
		}
		return pick_up_state_str;
	}
	
	//文字转数字
	public static int state_toInt(String state){
		int pick_up_state = 0;
		switch (state) {
		case "已提货":
			pick_up_state = PickUp.HAVE_PICKUP;
			break;
		case "未提货":
			pick_up_state = PickUp.HAVE_NOT_PICKUP;
			break;
		case "退货销毁":
			pick_up_state = PickUp.HAVE_DESTORYED_PICKUP;
			break;
		default: 
			pick_up_state = -1;
			break;
		}
		return pick_up_state;
	}
}
