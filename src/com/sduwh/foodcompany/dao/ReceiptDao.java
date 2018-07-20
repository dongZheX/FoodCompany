package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Receipt;
import com.sduwh.foodcompany.entity.Returned;

public interface ReceiptDao {
	/*
	 * 插入一条收据记录
	 */
	public void receiptByInsert(Map map);
	/*
	 * 根据键值receipt_id,order_id,receipt_money, teller_user_id,receipt_date查询
	 */
	public ArrayList<Receipt> findReceipt(Map map);
}
