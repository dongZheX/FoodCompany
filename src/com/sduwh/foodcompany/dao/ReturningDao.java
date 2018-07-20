package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Returned;
import com.sduwh.foodcompany.entity.Returning;

public interface ReturningDao {
	/*
	 * ����һ���˻�����¼
	 */
	public void insertReturned(Map map);
	/*
	 * ���ݼ�ֵreturn_id,return_money,sale_user_id, return_date��ѯ
	 */
	public ArrayList<Returning> findReturning(Map map);
}
