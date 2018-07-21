package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Ordered;

public interface OrderedDao {
	/*
	 * ��������
	 */
	public void insertOrder(Map map);
	public void getOrder(Map map);
	/*
	 * ��ѯ�����ܶ�
	 */
	public int selectOrder(Map map);
	/*
	 * ��ͨ����ֵorder_id,good_id,cus_user_id,sale_user_id,order_type
	 * order_date,pick_up_time_start,pick_up_time_end,order_state��ѯ
	 */
	public ArrayList<Ordered> findOrdered(Map map);
	/*
	 * ���޸�pick_up_time_start,pick_up_time_end,order_state����order_id
	 */
	public int updateOrdered(Map map);
}
