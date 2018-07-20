package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.ProducePlan;

public interface ProducePlanDao {
	/*
	 * ����һ��������¼
	 */
	public void insertProducePlan(Map map);
	/*
	 *��ͨ����ֵplan_id,good_id,deadline,plan_state,planer_user_id��ѯ
	 */
	public ArrayList<ProducePlan> findProducePlan(Map map);
	/*
	 * ���޸�deadline,plan_state���� plan_id
	 */
	public int updateProducePlan(Map map);
}
