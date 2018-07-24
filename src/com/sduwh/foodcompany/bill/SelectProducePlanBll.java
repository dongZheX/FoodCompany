package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.GoodsDao;
import com.sduwh.foodcompany.dao.ProducePlanDao;
import com.sduwh.foodcompany.dao.WarehouseDao;
import com.sduwh.foodcompany.entity.ProducePlan;
import com.sduwh.foodcompany.entity.Warehouse;

public class SelectProducePlanBll {
	
	
	
	/*
	 * ͨ��plan_id,good_id,good_num,deadline,plan_state,planer_user_id��ѯ�����ƻ�
	 */
	public static ArrayList<ProducePlan> select_ProducePlan(Object...args) {
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap(args);
		
		return dao.findProducePlan(map);
	}
	
	/*
	 * ͨ��good_id��good_num
	 */
	public static int select_Good_num(String good_id){
		SqlSession session = MybatisUtil.getSession();
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		Map map = MapBuilder.buildMap("good_id",good_id);
		ArrayList<Warehouse> warehouse_list= dao.findWareHouse(map);
		if(warehouse_list.size() == 0) return -1;
		else return warehouse_list.get(0).getGood_num();
	}
	
	/*
	 * ��ѯ�����ƻ���state�Ƿ�����޸�
	 */
	public static boolean select_state(String plan_id){
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap("plan_id",plan_id);
		int plan_state = dao.findProducePlan(map).get(0).getPlan_state();
		if(plan_state == ProducePlan.HAVE_NOT_CONFIRM){
			return true;
		}
		else
			return false;
	}
	
	/*
	 * �޸������ƻ�
	 */
	public static boolean alter_plan(Object...args){
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap(args);
		dao.updateProducePlan(map);
		session.commit();
		session.close();
		return true;
	}
	
	
	/*
	 * ȡ�������ƻ�
	 */
	public static boolean cancel_plan(String plan_id){
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap("plan_state",ProducePlan.HAVE_CANCELED,"plan_id",plan_id);
		dao.updateProducePlan(map);
		session.commit();
		session.close();
		return true;
	}
	/*
	 * ȷ�������ƻ�
	 */
	public static boolean confirm_plan(String plan_id){
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap("plan_state",ProducePlan.HAVE_PUT_INTO_PRODUCE,"plan_id",plan_id);
		dao.updateProducePlan(map);
		session.commit();
		session.close();
		return true;
	}
	/*
	 * ��������ƻ�
	 */
	public static boolean cometo_plan(String plan_id){
		SqlSession session = MybatisUtil.getSession();
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap("plan_state",ProducePlan.HAVE_FINISHED,"plan_id",plan_id);
		dao.updateProducePlan(map);
		session.commit();
		session.close();
		return true;
	}
}
