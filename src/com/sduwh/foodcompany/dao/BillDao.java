package com.sduwh.foodcompany.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Bill;

public interface BillDao {
	/*
	 * ����һ���˵���¼
	 */
	public void billByInsert(Map map);
	/*
	 * ��ͨ����ֵbill_date,bill_money,bill_date,receipt_id,accountant_id��ѯ
	 */
	public ArrayList<Bill> findBill(Map map);
}
