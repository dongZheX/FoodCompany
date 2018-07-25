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
	//万能
	public static final int BEST = 9;
	private int adm_power;
	//默认构造方法
	public Administrators(){
		user_id = "";
		user_name = "";
		user_psw = "";
		user_tel = "";
		adm_power = 0;//默认
	}
	
	public Administrators(String user_id, String user_name, String user_psw, String user_tel, int adm_power) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_psw = user_psw;
		this.user_tel = user_tel;
		this.adm_power = adm_power;
	}
	
	//getter and setters
	public int getAdm_power() {
		return adm_power;
	}

	public void setAdm_power(int adm_power) {
		this.adm_power = adm_power;
	}

	@Override
	public String toString() {
		return "Administrators [adm_power=" + adm_power + ", user_id=" + user_id + ", user_name=" + user_name
				+ ", user_psw=" + user_psw + ", user_tel=" + user_tel + "]";
	}
	
	/*String[] combobox_item = {"系统管理员", "成品库管理员","会计","出纳","生产车间管理员","生产计划管理员","销售"};*/
	public static int administrators_string_toInt(String type) {
		switch(type) {
		case "系统管理员": return 2;
		case "成品库管理员": return 3;
		case "会计": return 4;
		case "出纳": return 5;
		case "生产车间管理员": return 6;
		case "生产计划管理员": return 7;
		case "销售": return 8;
		case "万能": return 9;
		}
		return 0;
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
