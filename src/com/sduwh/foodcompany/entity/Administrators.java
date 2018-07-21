package com.sduwh.foodcompany.entity;

import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Administrators表，继承自User表，实现toString，实现equals
 */
public class Administrators extends User implements Serializable{
	//系统管理员
	public static final int SYSTEM_ADMIN_NUM = 2;
	//成品库管理员
	public static final int WAREHOUSE_ADMIN_NUM = 3;
	//会计管理员
	public static final int ACCOUNTANT_ADMIN_NUM = 4;
	//出纳管理员
	public static final int TELLER_ADMIN_NUM = 5;
	//生产车间管理员
	public static final int PRODUCE_ADMIN_NUM = 6;
	//生产计划科管理员
	public static final int PRODUCE_PLAN_ADMIN_NUM = 7;
	//销售管理员
	public static final int SALE_ADMIN_NUM = 8;
	
	private int adm_power;
	//默认构造方法
	public Administrators(){
		user_id = "";
		user_name = "";
		user_psw = "";
		user_tel = "";
		adm_power = 0;//默认
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
