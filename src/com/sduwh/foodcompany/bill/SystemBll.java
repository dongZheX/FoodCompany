package com.sduwh.foodcompany.bill;

import java.util.HashMap;
import java.util.Map;

import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.DaoFactory;
import com.sduwh.foodcompany.entity.Administrators;

public class SystemBll {
	/*创建员工*/
	/*user_id, user_name, user_psw, user_tel, adm_power*/
	public static void  createAdministrators(String user_name, String user_psw, String user_tel, String adm_power) {
		int power = 0;
		switch(adm_power) {
		case "系统管理员": 	power = 2;
		break;
		case "成品库管理员": power = 3;
		break;
		case "会计":		power = 4;
		break;
		case "出纳":		power = 5;
		break;
		case "生产车间管理员":	power = 6;
		break;
		case "生产计划科管理员":	power = 7;
		break;
		case "销售":		power = 8;
		break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("user_name", user_name);
		map.put("user_psw", MD5.getMD5(adm_power));
		map.put("user_tel", user_tel);
		map.put("adm_power", power);
		AdministratorsDao dao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
		dao.insertAdministrators(map);
	}
	
	/*按ID搜索员工*/
	public static Administrators searchAdministratorsById(String user_id) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		
		AdministratorsDao administratorsDao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
		return administratorsDao.findAdministrators(map).get(0);
	}
	
	public static Administrators[] searchAdministratorsByName(String user_name) {
		Map<String, String> map = new HashMap<>();
		map.put("user_name", user_name);
		
		AdministratorsDao administratorsDao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
		return (Administrators[])administratorsDao.findAdministrators(map).toArray();
	}
	
	public static void updateAdministrators(Administrators admin) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("user_id", admin.getUser_id());
		map.put("user_name", admin.getUser_name());
		map.put("user_psw", MD5.getMD5(admin.getUser_psw()));
		map.put("user_tel", admin.getUser_tel());
		map.put("user_power", admin.getAdm_power());
		
		AdministratorsDao dao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
		dao.insertAdministrators(map);
	}
}
