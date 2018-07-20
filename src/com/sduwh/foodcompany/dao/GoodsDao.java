package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Goods;

public interface GoodsDao {
	/*
	 * 创建商品
	 */
	public int insertGood(Map map);
	/*
	 * 可通过键值good_id,good_name(模糊),good_standard,good_cost,good_expiration_date查询
	 */
	public ArrayList<Goods> findGoods(Map map);
	/*
	 * 可修改good_name,good_stadard,good_cost,good_expiration_date根据good_id
	 */
}
