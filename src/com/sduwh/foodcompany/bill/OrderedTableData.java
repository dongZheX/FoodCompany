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
		case Ordered.ORDER_HUO_AFT:	return "ԤԼ��";
		case Ordered.ORDER_HUO_BEF:	return "ԤԼ�ȸ�";
		case Ordered.XIAN_HUO_AFT:	return "�ֻ���";
		case Ordered.XIAN_HUO_BEF:	return "�ֻ��ȸ�";
		}
		return null;
	}
	public float getSum() {
		return sum;
	}
	
}
