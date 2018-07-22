package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Warehouse;

public interface WarehouseDao {
	/*
	 * 插入一条库存信息
	 */
	public void insertWarehouse(Map map);
	/*
	 * 根据键值batch_id,good_id,good_PD,good_GP,warehouse_user_id,workshop_user_id,good_state
	 * 查询记录
	 */
	public  ArrayList<Warehouse> findWareHouse(Map map);
	/*
	 * 根据batch_id修改good_state
	 */
	public int updateWarehouse(Map map);
	/*
	 * 查询快过期商品
	 */
	public  ArrayList<Warehouse> findWareHouseOutOfDateAllMore();
	/*
	 * 刷新过期问题
	 */
	public void out_of_date_check();
}
