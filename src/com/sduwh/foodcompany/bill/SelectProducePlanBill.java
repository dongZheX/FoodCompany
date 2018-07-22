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

public class SelectProducePlanBill {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	/*
	 * 通过plan_id,good_id,good_num,deadline,plan_state,planer_user_id查询生产计划
	 */
	public static ArrayList<ProducePlan> select_ProducePlan(Object...args) {
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap(args);
		return dao.findProducePlan(map);
	}
	
	/*
	 * 通过good_id查good_num
	 */
	public static int select_Good_num(String good_id){
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		Map map = MapBuilder.buildMap("good_id",good_id);
		return dao.findWareHouse(map).get(0).getGood_num();
	}
	
	/*
	 * 修改生产计划
	 */
	public static boolean alter_plan(Object...args){
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap(args);
		System.out.println(dao.findProducePlan(map));
		int state = dao.findProducePlan(map).get(0).getPlan_state();
		if(!(state == ProducePlan.HAVE_NOT_CONFIRM)){
			return false;
		}
		else{
			dao.updateProducePlan(map);
			return true;
		}
		
	}
}
