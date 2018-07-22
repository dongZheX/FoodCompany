package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Warehouse;

public interface WarehouseDao {
	/*
	 * ����һ�������Ϣ
	 */
	public void insertWarehouse(Map map);
	/*
	 * ���ݼ�ֵbatch_id,good_id,good_PD,good_GP,warehouse_user_id,workshop_user_id,good_state
	 * ��ѯ��¼
	 */
	public  ArrayList<Warehouse> findWareHouse(Map map);
	/*
	 * ����batch_id�޸�good_state
	 */
	public int updateWarehouse(Map map);
	/*
	 * ��ѯ�������Ʒ
	 */
	public  ArrayList<Warehouse> findWareHouseOutOfDateAllMore();
	/*
	 * ˢ�¹�������
	 */
	public void out_of_date_check();
}
