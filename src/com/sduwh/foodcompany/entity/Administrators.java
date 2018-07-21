package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author ����
 * @date 2018/7/20
 * ӳ�����ݿ��е�Administrators���̳���User��ʵ��toString��ʵ��equals
 */
public class Administrators extends User implements Serializable{
	//ϵͳ����Ա
	public static final int SYSTEM_ADMIN_NUM = 2;
	//��Ʒ�����Ա
	public static final int WAREHOUSE_ADMIN_NUM = 3;
	//��ƹ���Ա
	public static final int ACCOUNTANT_ADMIN_NUM = 4;
	//���ɹ���Ա
	public static final int TELLER_ADMIN_NUM = 5;
	//�����������Ա
	public static final int PRODUCE_ADMIN_NUM = 6;
	//�����ƻ��ƹ���Ա
	public static final int PRODUCE_PLAN_ADMIN_NUM = 7;
	//���۹���Ա
	public static final int SALE_ADMIN_NUM = 8;
	
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
