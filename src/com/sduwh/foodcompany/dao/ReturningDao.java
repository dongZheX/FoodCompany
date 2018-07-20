package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Returned;
import com.sduwh.foodcompany.entity.Returning;

public interface ReturningDao {
	/*
	 * 插入一条退货单记录
	 */
	public void insertReturned(Map map);
	/*
	 * 根据键值return_id,return_money,sale_user_id, return_date查询
	 */
	public ArrayList<Returning> findReturning(Map map);
}
