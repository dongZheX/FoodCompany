package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Receipt;
import com.sduwh.foodcompany.entity.Returned;

public interface ReceiptDao {
	/*
	 * ����һ���վݼ�¼
	 */
	public void receiptByInsert(Map map);
	/*
	 * ���ݼ�ֵreceipt_id,order_id,receipt_money, teller_user_id,receipt_date��ѯ
	 */
	public ArrayList<Receipt> findReceipt(Map map);
}
