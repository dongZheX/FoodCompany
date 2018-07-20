package com.sduwh.foodcompany.entity;

import java.io.Serializable;
import java.util.Date;
/*
 * @author ����
 * @date 2018/7/20
 * ӳ�����ݿ��е�Warehouse����ʵ��toString��ʵ��equals
 */
public class Warehouse implements Serializable{
	private String batch_id;
	private String good_id;
	private Date good_PD;
	private Date good_GP;
	private String warehouse_user_id;
	private String workshop_user_id;
	private int good_state;
	//Ĭ�Ϲ��캯��
	public Warehouse() {
		batch_id = "";
		good_id = null;
		good_PD = null;
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
	@Override
	public String toString() {
		return "Warehouse [batch_id=" + batch_id + ", good_id=" + good_id + ", good_PD=" + good_PD + ", good_GP="
				+ good_GP + ", warehouse_user_id=" + warehouse_user_id + ", workshop_user_id=" + workshop_user_id
				+ ", good_state=" + good_state + "]";
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