package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.ProducePlan;

public interface ProducePlanDao {
	/*
	 * 插入一个生产记录
	 */
	public void insertProducePlan(Map map);
	/*
	 *可通过键值plan_id,good_id,deadline,plan_state,planer_user_id查询
	 */
	public ArrayList<ProducePlan> findProducePlan(Map map);
	/*
	 * 可修改deadline,plan_state根据 plan_id
	 */
	public int updateProducePlan(Map map);
}
