package com.sduwh.foodcompany.bill;

import com.sduwh.foodcompany.entity.Ordered;

public class OrderedTableData {
	private String orderedId, customerId, customerName;
	private int type, state;
	private float sum;
	public OrderedTableData(String orderedId, String customerId, String customerName, int type, int state, float sum) {
		this.orderedId = orderedId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.type = type;
		this.state = state;
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
		case Ordered.ORDER_HUO_AFT:	return "ԤԼ(��)";
		case Ordered.ORDER_HUO_BEF:	return "ԤԼ(�ȸ�)";
		case Ordered.XIAN_HUO_AFT:	return "�ֻ�(��)";
		case Ordered.XIAN_HUO_BEF:	return "�ֻ�(�ȸ�)";
		}
		return null;
	}
	public String getState() {
		switch(this.state) {
		case Ordered.UMPAID:		return "δ����";
		case Ordered.PAID_PART:		return "�Ѹ�����";
		case Ordered.PAID_ALL:		return "�Ѹ�ȫ��";
		case Ordered.PAID_CANCEL:	return "��ȡ��";
		}
		return null;
	}
	public float getSum() {
		return sum;
	}
	public String[] toArray() {
		String[] ans = {
				this.orderedId,this.customerId, this.customerName, this.getType(), this.getState(), String.format("%f", this.sum)
		};
		return ans;
	}
	
}
