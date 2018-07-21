package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Ordered;

public interface OrderedDao {
	/*
	 * 创建订单
	 */
	public void insertOrder(Map map);
	public void getOrder(Map map);
	/*
	 * 查询订单总额
	 */
	public int selectOrder(Map map);
	/*
	 * 可通过键值order_id,good_id,cus_user_id,sale_user_id,order_type
	 * order_date,pick_up_time_start,pick_up_time_end,order_state查询
	 */
	public ArrayList<Ordered> findOrdered(Map map);
	/*
	 * 可修改pick_up_time_start,pick_up_time_end,order_state根据order_id
	 */
	public int updateOrdered(Map map);
}
