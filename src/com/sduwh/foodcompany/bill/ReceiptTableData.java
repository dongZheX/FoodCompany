package com.sduwh.foodcompany.bill;

/**
 * 会计查看收据的时候，需要在JTable中显示的属性
 * @author 李世鹏
 *
 */
public class ReceiptTableData {
	//收据id，订单id,客户id，客户姓名，收款金额
	String receiptId, orderId, customerId, customerName;
	float money;
	
	public ReceiptTableData(String receiptId, String orderId, String customerId, String customerName, float money) {
		this.receiptId = receiptId;
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.money = money;
	}
	
	public String getReceiptId() {
		return this.receiptId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public float getMoney() {
		return this.money;
	}
}
