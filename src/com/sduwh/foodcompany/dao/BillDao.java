package com.sduwh.foodcompany.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Bill;

public interface BillDao {
	/*
	 * 插入一个账单记录
	 */
	public void billByInsert(Map map);
	/*
	 * 可通过键值bill_date,bill_money,bill_date,receipt_id,accountant_id查询
	 */
	public ArrayList<Bill> findBill(Map map);
}
