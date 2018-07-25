package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.comm.MD5;

public class AdministratorsTableData {
	private String name, tel, psw;
	private int type;
	
	public AdministratorsTableData(String name, String psw, String tel, int type) {
		this.name = name;
		this.psw = MD5.getMD5(psw);
		this.tel = tel;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPsw() {
		return this.psw;
	}
	
	public String getTel() {
		return this.tel;
	}
	
	public int getType() {
		return this.type;
	}
}
