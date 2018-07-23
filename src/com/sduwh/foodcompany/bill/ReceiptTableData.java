package com.sduwh.foodcompany.bill;

/**
 * ��Ʋ鿴�վݵ�ʱ����Ҫ��JTable����ʾ������
 * @author ������
 *
 */
public class ReceiptTableData {
	//�վ�id������id,�ͻ�id���ͻ��������տ���
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
