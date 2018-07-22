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
	 * 通过管理员姓名和权限获取管理员实体列表
	 * @param adm_user_name 管理员信息 adm_power 管理员权限
	 */
	public static ArrayList<Administrators> Administrators_select(String adm_user_name,String adm_power){
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_name", adm_user_name);
		map.put("adm_power", adm_power);
		return dao.findAdministrators(map);
	}
	/*
	 * 通过提货单编号获取订货单实体列表
	 * @param pick_up_id 提货单编号
	 */
	public static ArrayList<Ordered> PickUp_select_Ordered(String pick_up_id){
		OrderedDao dao = session.getMapper(OrderedDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", pick_up_id);
		return dao.findOrdered(map);
	}
	
	/*
	 * 通过提货单信息获取提货单实体列表
	 * @param pick_up_id 提货单编号等..
	 */
	public static ArrayList<PickUp> PickUp_select(Object...args){
		PickUpDao dao = session.getMapper(PickUpDao.class);
		Map<String, Object> map = MapBuilder.buildMap(args);
		return dao.findPickUp(map);
	}
	
}
