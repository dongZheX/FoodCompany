package com.sduwh.foodcompany.bill;

import java.util.Map;

import javax.mail.Session;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.GoodsDao;
import com.sduwh.foodcompany.dao.ProducePlanDao;
import com.sduwh.foodcompany.entity.ProducePlan;

public class InsertProducePlanBill {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	/*
	 * 通过输入good_name 返回good_id
	 */
	public static String select_good_id(String good_name){
		GoodsDao dao = session.getMapper(GoodsDao.class);
		Map map = MapBuilder.buildMap("good_name",good_name.equals("")?null:good_name);
		if(dao.findGoods(map).size() == 0)
			return "error";
		else
			return dao.findGoods(map).get(0).getGood_id();
	}
	
	/*
	 * 添加一个生产计划记录
	 */
	public static boolean add_plan(Object...args){
		ProducePlanDao dao = session.getMapper(ProducePlanDao.class);
		Map map = MapBuilder.buildMap(args);
		dao.insertProducePlan(map);
		session.commit();
		return true;
	}
}
