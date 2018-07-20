package com.sduwh.foodcompany.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Goods;

public interface AdministratorsDao {
	/*
	 * ��������Ա
	 */
	public void insertAdministrators(Map map);
	/*
	 * ��ͨ����ֵuser_id,user_name,user_tel,adm_power��ѯ
	 */
	public ArrayList<Administrators> findAdministrators(Map map);
	/*
	 * ���޸�user_tel,adm_power����user_id
	 */
	public int updateAdministrators(Map map);
}
