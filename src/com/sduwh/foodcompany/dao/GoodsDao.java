package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Goods;

public interface GoodsDao {
	/*
	 * ������Ʒ
	 */
	public int insertGood(Map map);
	/*
	 * ��ͨ����ֵgood_id,good_name(ģ��),good_standard,good_cost,good_expiration_date��ѯ
	 */
	public ArrayList<Goods> findGoods(Map map);
	/*
	 * ���޸�good_name,good_stadard,good_cost,good_expiration_date����good_id
	 */
}
