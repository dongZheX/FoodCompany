package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author ����
 * @date 2018/7/20
 * ӳ�����ݿ��е�Administrators���̳���User��ʵ��toString��ʵ��equals
 */
public class Administrators extends User implements Serializable{
	private int adm_power;
	//Ĭ�Ϲ��췽��
	public Administrators(){
		user_id = "";
		user_name = "";
		user_psw = "";
		user_tel = "";
		adm_power = 0;//Ĭ��
	}
	//getter and setters
	protected int getAdm_power() {
		return adm_power;
	}

	protected void setAdm_power(int adm_power) {
		this.adm_power = adm_power;
	}

	@Override
	public String toString() {
		return "Administrators [adm_power=" + adm_power + ", user_id=" + user_id + ", user_name=" + user_name
				+ ", user_psw=" + user_psw + ", user_tel=" + user_tel + "]";
	}
	public boolean equals(Administrators c) {
		if(c instanceof Administrators) {
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
