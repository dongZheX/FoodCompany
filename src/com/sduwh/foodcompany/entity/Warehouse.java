package com.sduwh.foodcompany.entity;

import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Date;
/*
 * @author 董喆
 * @date 2018/7/21
 * 映射数据库中的Warehouse表，实现toString，实现equals
 */
public class Warehouse implements Serializable{
	//正常
	public static final int WAREHOUSE_NORMAL = 1;
	//售空
	public static final int WAREHOUSE_EMPTY = 2;
	//过期
	public static final int WAREHOUSE_OUT_OF_DATE = 3;
	//已销毁
	public static final int WAREHOUSE_DESTORYED = 4;
	private String batch_id;
	private String good_id;
	private Date good_PD;
	private Date good_GP;
	private int good_num;
	private String warehouse_user_id;
	private String workshop_user_id;
	private int good_state;
	//默认构造函数
	public Warehouse() {
		batch_id = "";
		good_id = null;
		good_PD = null;
		good_num = 0;
		warehouse_user_id = "";
		good_state = 0;
	}
	//getters and setters
	public String getBatch_id() {
		return batch_id;
	}
	public String getWorkshop_user_id() {
		return workshop_user_id;
	}
	public void setWorkshop_user_id(String workshop_user_id) {
		this.workshop_user_id = workshop_user_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public Date getGood_PD() {
		return good_PD;
	}
	public void setGood_PD(Date good_PD) {
		
		this.good_PD = good_PD;
	}
	public Date getGood_GP() {
		return good_GP;
	}
	public void setGood_GP(Date good_GP) {
		this.good_GP = good_GP;
	}
	public String getWarehouse_user_id() {
		return warehouse_user_id;
	}
	public void setWarehouse_user_id(String warehouse_user_id) {
		this.warehouse_user_id = warehouse_user_id;
	}
	public int getGood_state() {
		return good_state;
	}
	public void setGood_state(int good_state) {
		this.good_state = good_state;
	}
	
	public int getGood_num() {
		return good_num;
	}
	public void setGood_num(int good_num) {
		this.good_num = good_num;
	}
	@Override
	public String toString() {
		return "Warehouse [batch_id=" + batch_id + ", good_id=" + good_id + ", good_PD=" + good_PD + ", good_GP="
				+ good_GP + ", good_num=" + good_num + ", warehouse_user_id=" + warehouse_user_id
				+ ", workshop_user_id=" + workshop_user_id + ", good_state=" + good_state + "]";
	}
	public boolean equals(Warehouse c) {
		if(c instanceof Warehouse) {
			String id = c.getBatch_id();
			if(id.equals(getBatch_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
}
