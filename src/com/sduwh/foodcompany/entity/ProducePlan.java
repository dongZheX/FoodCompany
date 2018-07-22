package com.sduwh.foodcompany.entity;

import java.io.Serializable;
import java.sql.Date;
/*
 * @author 董喆
 * @date 2018/7/21
 * 映射数据库中的ProducePlan表，实现toString，实现equals
 */
public class ProducePlan implements Serializable{
	//未确认
	public static final int HAVE_NOT_CONFIRM = 1;
	//已投入生产
	public static final int HAVE_PUT_INTO_PRODUCE = 2;
	//入库
	public static final int HAVE_FINISHED = 3;
	//取消
	public static final int HAVE_CANCELED = 4;
	private String plan_id;
	private String good_id;
	private int good_num;
	private Date deadline;
	private int plan_state;
	private String planer_user_id;
	//默认构造函数
	private ProducePlan() {
		plan_id = "";
		good_id = "";
		good_num = 0;
		deadline = null;
		plan_state = 0;
		planer_user_id = "";
	}
	//getters and setters
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public int getGood_num() {
		return good_num;
	}
	public void setGood_num(int good_num) {
		this.good_num = good_num;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getPlan_state() {
		return plan_state;
	}
	public void setPlan_state(int plan_state) {
		this.plan_state = plan_state;
	}
	public String getPlaner_user_id() {
		return planer_user_id;
	}
	public void setPlaner_user_id(String planer_user_id) {
		this.planer_user_id = planer_user_id;
	}
	@Override
	public String toString() {
		return "ProducePlan [plan_id=" + plan_id + ", good_id=" + good_id + ", good_num=" + good_num + ", deadline="
				+ deadline + ", plan_state=" + plan_state + ", planer_user_id=" + planer_user_id + "]";
	}
	public boolean equals(ProducePlan c) {
		if(c instanceof ProducePlan) {
			String id = c.getPlan_id();
			if(id.equals(c.getPlan_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
