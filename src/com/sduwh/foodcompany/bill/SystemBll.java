package com.sduwh.foodcompany.bill;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.DaoFactory;
import com.sduwh.foodcompany.entity.Administrators;

public class SystemBll {
	/*����Ա��*/
	/*user_id, user_name, user_psw, user_tel, adm_power*/
	public static void  createAdministrators(String user_name, String user_psw, String user_tel, String adm_power) {
		int power = 0;
		switch(adm_power) {
		case "ϵͳ����Ա": 	power = 2;
		break;
		case "��Ʒ�����Ա": power = 3;
		break;
		case "���":		power = 4;
		break;
		case "����":		power = 5;
		break;
		case "�����������Ա":	power = 6;
		break;
		case "�����ƻ�����Ա":	power = 7;
		break;
		case "����":		power = 8;
		break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", user_name);
		map.put("psw", MD5.getMD5(adm_power));
		map.put("tel", user_tel);
		map.put("power", power);
		AdministratorsDao dao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
		dao.insertAdministrators(map);
		
		/*SqlSession session = MybatisUtil.getSession();
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		dao.insertAdministrators(map);
		session.commit();*/
	}
	
	/*��ID����Ա��*/
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
	
	public static AdministratorsTableData[] searchAdministrators(String user_id, String user_name) {
		Map map = MapBuilder.buildMap("user_id", user_id, "user_name", user_name);
		SqlSession session = MybatisUtil.getSession();
		//AdministratorsDao dao = session.getClass()
		return null;
	}
}
