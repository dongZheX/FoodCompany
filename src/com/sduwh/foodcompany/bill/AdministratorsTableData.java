package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.comm.MD5;

public class AdministratorsTableData {
	private String name, tel, psw, id;
	private int type;
	
	public AdministratorsTableData(String id, String name, String psw, String tel, int type) {
		this.name = name;
		this.psw = MD5.getMD5(psw);
		this.tel = tel;
		this.type = type;
		this.id = id;
	}
	
	public String getId() {
		return this.id;
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
	
	public String[] toArray() {
		String[] s = {
				this.id, this.name, this.tel, this.psw, String.format("%d", this.type)
		};
		return s;
	}
}
