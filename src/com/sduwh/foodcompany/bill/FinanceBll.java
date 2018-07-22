package com.sduwh.foodcompany.bill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sduwh.foodcompany.comm.*;
import com.sduwh.foodcompany.dao.*;
import com.sduwh.foodcompany.entity.*;

public class FinanceBll {
	/**
	 * 开具账单
	 * @param order_id
	 * 收据表的内容：receiped_id, order_id, receipt_money, teller_user_id
	 * 			receipted_date
	 */
	/*
	public static void createReceipt(OrderedTableData data, String teller_user_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", data.getOrderedId());
		map.put("receipt_money", data.getSum());
		map.put("teller_user_id", teller_user_id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		map.put("receipted_date", dateStr);
		
		ReceiptDao dao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		dao.receiptByInsert(map);
	}
	
	private static boolean needCreatePickUp(OrderedTableData data) {
		String type = data.getType();
		String OrderId = data.getOrderedId();
		String CustomerId = data.getCustomerId();
		
		Map map = MapBuilder.buildMap("user_id", CustomerId);
		CustomerDao dao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		float score = dao.findCustomer(map).get(0).getCus_score();
		
		if(score >= 80 && (type.equals("现货先付") || type.equals("预约现付")))
		return false;
	}*/
	
	/**
	 * 
	 */
}
