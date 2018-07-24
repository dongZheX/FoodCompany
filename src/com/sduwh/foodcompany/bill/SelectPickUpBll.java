package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.PickUpDao;
import com.sduwh.foodcompany.entity.PickUp;

public class SelectPickUpBll {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	
	/*
	 * ��ͨ��pick_up_id,pick_up_date,accountant_user_id��ѯ�������Ϣ
	 */
	public static ArrayList<PickUp> find_pick_up(Object...args){
		SqlSession session = MybatisUtil.getSession();
		PickUpDao dao = session.getMapper(PickUpDao.class);
		Map map = MapBuilder.buildMap(args);
		ArrayList<PickUp> pick_up_list =  dao.findPickUp(map);
		session.close();
		return pick_up_list;
	}
	/*
	 * ��ͨ��pick_up_id �޸������״̬Ϊ �����
	 */
	public static boolean alter_state(String pick_up_id){
		//SqlSession session = MybatisUtil.getSession();
		PickUpDao dao = session.getMapper(PickUpDao.class);
		Map map_1 = MapBuilder.buildMap("pick_up_id",pick_up_id);
		if(dao.findPickUp(map_1).get(0).getPick_up_state() == PickUp.HAVE_NOT_PICKUP){
			Map map = MapBuilder.buildMap("pick_up_state",PickUp.HAVE_PICKUP,"pick_up_id",pick_up_id);
			dao.updatePickUp(map);
			session.commit();		
			return true;
		}
		return false;
	}
	
}
