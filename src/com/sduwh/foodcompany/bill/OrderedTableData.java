package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.entity.Ordered;

public class OrderedTableData {
	private String orderedId, customerId, customerName;
	private int type;
	private float sum;
	public OrderedTableData(String orderedId, String customerId, String customerName, int type, float sum) {
		this.orderedId = orderedId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.type = type;
		this.sum = sum;
	}
	
	public String getOrderedId() {
		return this.orderedId;
	}
	public String getCustomerId() {
		return this.customerId;
	}
	public String getCustomerName() {
		return this.customerName;
	}
	public String getType() {
		switch(this.type) {
		case Ordered.ORDER_HUO_AFT:	return "预约后付";
		case Ordered.ORDER_HUO_BEF:	return "预约先付";
		case Ordered.XIAN_HUO_AFT:	return "现货后付";
		case Ordered.XIAN_HUO_BEF:	return "现货先付";
		}
		return null;
	}
	public float getSum() {
		return sum;
	}
	
}
