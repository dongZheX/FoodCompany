package com.sduwh.foodcompany.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Goods;

public interface AdministratorsDao {
	/*
	 * 创建管理员
	 */
	public void insertAdministrators(Map map);
	/*
	 * 可通过键值user_id,user_name,user_tel,adm_power查询
	 */
	public ArrayList<Administrators> findAdministrators(Map map);
	/*
	 * 可修改user_tel,adm_power根据user_id
	 */
	public int updateAdministrators(Map map);
}
