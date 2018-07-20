package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Returned;
import com.sduwh.foodcompany.entity.Returning;

public interface ReturnedDao {
	/*
	 * 插入一条确认退货单记录
	 */
	public void insertReturned(Map map);
	/*
	 * 根据键值returned_id,return_id,teller_user_id, returned_date查询
	 */
	public ArrayList<Returned> findReturned(Map map);
}
