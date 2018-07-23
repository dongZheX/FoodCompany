package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.PickUpDao;
import com.sduwh.foodcompany.entity.PickUp;

public class SelectPickUpBll {
	
	/*
	 * 可通过pick_up_id,pick_up_date,accountant_user_id查询提货单信息
	 */
	public static ArrayList<PickUp> find_pick_up(Object...args){
		SqlSession session = MybatisUtil.getSession();
		PickUpDao dao = session.getMapper(PickUpDao.class);
		Map map = MapBuilder.buildMap(args);
		return dao.findPickUp(map);	
	}
}
