package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.PickUp;

public interface PickUpDao {
	/*
	 * 创建提货单
	 */
	public void insertPickUp(Map map);
	/*
	 * 可通过键值pick_up_id,pick_up_state,accountant_user_id查询
	 */
	public ArrayList<PickUp> findPickUp(Map map);
	/*
	 * 可修改pick_up_state根据pick_up_id
	 */
	public int updatePickUp(Map map);
}
