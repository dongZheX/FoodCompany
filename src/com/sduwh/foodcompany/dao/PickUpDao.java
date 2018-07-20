package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.PickUp;

public interface PickUpDao {
	/*
	 * ���������
	 */
	public void insertPickUp(Map map);
	/*
	 * ��ͨ����ֵpick_up_id,pick_up_state,accountant_user_id��ѯ
	 */
	public ArrayList<PickUp> findPickUp(Map map);
	/*
	 * ���޸�pick_up_state����pick_up_id
	 */
	public int updatePickUp(Map map);
}
