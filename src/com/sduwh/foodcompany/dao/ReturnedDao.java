package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Returned;
import com.sduwh.foodcompany.entity.Returning;

public interface ReturnedDao {
	/*
	 * ����һ��ȷ���˻�����¼
	 */
	public void insertReturned(Map map);
	/*
	 * ���ݼ�ֵreturned_id,return_id,teller_user_id, returned_date��ѯ
	 */
	public ArrayList<Returned> findReturned(Map map);
}
