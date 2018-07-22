package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.OrderedDao;
import com.sduwh.foodcompany.dao.PickUpDao;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Ordered;
import com.sduwh.foodcompany.entity.PickUp;

public class NameToEntity {
	
	
	
	private static SqlSession session = MybatisUtil.getSession();
	
	
	/*
	 * ͨ������Ա������Ȩ�޻�ȡ����Աʵ���б�
	 * @param adm_user_name ����Ա��Ϣ adm_power ����ԱȨ��
	 */
	public static ArrayList<Administrators> Administrators_select(String adm_user_name,String adm_power){
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_name", adm_user_name);
		map.put("adm_power", adm_power);
		return dao.findAdministrators(map);
	}
	/*
	 * ͨ���������Ż�ȡ������ʵ���б�
	 * @param pick_up_id ��������
	 */
	public static ArrayList<Ordered> PickUp_select_Ordered(String pick_up_id){
		OrderedDao dao = session.getMapper(OrderedDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", pick_up_id);
		return dao.findOrdered(map);
	}
	
	/*
	 * ͨ���������Ϣ��ȡ�����ʵ���б�
	 * @param pick_up_id �������ŵ�..
	 */
	public static ArrayList<PickUp> PickUp_select(Object...args){
		PickUpDao dao = session.getMapper(PickUpDao.class);
		Map<String, Object> map = MapBuilder.buildMap(args);
		return dao.findPickUp(map);
	}
	
}
