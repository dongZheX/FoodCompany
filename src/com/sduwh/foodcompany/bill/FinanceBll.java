package com.sduwh.foodcompany.bill;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sduwh.foodcompany.comm.*;
import com.sduwh.foodcompany.dao.*;
import com.sduwh.foodcompany.entity.*;

public class FinanceBll {
	/*================================================================�����ǻ�Ƶ��߼�ҵ��ʵ��=====================================================*/
	
	/*�����˵�
	 * bill_id, bill_date, bill_money, receipt_id, accountant_user_id
	 */
	public static void createBill(ReceiptTableData receiptTableData, String accountantId) {
		Map<String, Object> map = new HashMap<>();
		map.put("bill_date", getDate());
		map.put("bill_money", receiptTableData.getMoney());
		map.put("receipt_id", receiptTableData.getReceiptId());
		map.put("accountant_user_id", accountantId);
		
		BillDao billDao = (BillDao)DaoFactory.createDao(DaoFactory.DAO_BILL);
		billDao.billByInsert(map);
		
		/*�����Ҫ����������Ļ����Ϳ��������*/
		if(FinanceBll.needCreatePickUp(FinanceBll.searchOrderByOrderId(receiptTableData.getOrderId()))) {
			createPickUp(receiptTableData, accountantId);
		}
	}
	
	/*
	 * ���������
	 * pick_up_id(����order_id), pick_up_state, accountant_user_id
	 */
	private static void createPickUp(ReceiptTableData receiptTableData, String accountantId) {
		Map<String, Object> map = new HashMap<>();
		map.put("pick_up_id", receiptTableData.getOrderId());
		map.put("pick_up_state", PickUp.HAVE_NOT_PICKUP);
		map.put("accountant_user_id", accountantId);
		PickUpDao pickUpDao = (PickUpDao)DaoFactory.createDao(DaoFactory.DAO_PICKUP);
		pickUpDao.insertPickUp(map);
	}
	
	/*
	 * �ж��Ƿ���Ҫ���������
	 */
	private static boolean needCreatePickUp(OrderedTableData data) {
		String type = data.getType();
		String state = data.getState();
		String CustomerId = data.getCustomerId();
		
		Map map = MapBuilder.buildMap("user_id", CustomerId);
		CustomerDao dao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		float score = dao.findCustomer(map).get(0).getCus_score();
		
		if		(score >= 80 && (type.equals("�ֻ���")	 	|| type.equals("ԤԼ��")) && state.equals("�Ѹ�����"))	return true;
		else if	(score < 80  && (type.equals("�ֻ���") 	|| type.equals("ԤԼ��")) && state.equals("�Ѹ�β��"))	return true;
		else if ((type.equals("�ֻ��ȸ�") 					|| type.equals("ԤԼ�ȸ�")) && state.equals("�Ѹ�β��"))	return true;
		return false;
	}
	
	/*
	 * �����վ�Id�ҵ��վ�
	 */
	public static ReceiptTableData searchReceiptByReceiptId(String receiptId) {
		if(receiptId == "")	receiptId = null;
		Map map = MapBuilder.buildMap("receipt_id", receiptId);
		ReceiptDao receiptDao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		ArrayList<Receipt> arrayList = receiptDao.findReceipt(map);
		if(arrayList.size() == 0)	return null;	/*������ݿ���û�иö������򷵻�null*/
		Receipt receipt = arrayList.get(0);	//�ҵ�����վ�
		
		Ordered order = FinanceBll.getOrderById(receipt.getOrder_id());
		Customer customer = FinanceBll.getCustomerById(order.getCus_user_id());
		
		return new ReceiptTableData(receipt.getReceipt_id(), order.getOrder_id(), customer.getUser_id(), customer.getUser_name(), receipt.getReceipt_money()); 
	}
	
	/*
	 * ���ݶ���Id�ҵ��վ�
	 */
	public static ReceiptTableData searchReceiptByOrderId(String orderId) {
		if(orderId == "")	orderId = null;
		Ordered order = FinanceBll.getOrderById(orderId);
		if(order == null)	return null;
		int orderState = order.getOrder_state();
		Map getReceipt = MapBuilder.buildMap("order_id", orderId);
		ReceiptDao receiptDao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		ArrayList<Receipt> arrayList = (ArrayList)receiptDao.findReceipt(getReceipt);
		if(arrayList.size() == 0)	return null;
		/*ͨ��forѭ���ҵ��վ�*/
		Receipt receipt = null;
		for(int i = 0; i < arrayList.size(); ++i) {
			receipt = arrayList.get(i);
			char receiptType = receipt.getOrder_id().charAt(receipt.getOrder_id().length() - 1) ;
			if(receiptType == 'A' && orderState == Ordered.PAID_ALL || receiptType == 'B' && orderState == Ordered.PAID_PART || receiptType == 'C' && orderState == Ordered.PAID_ALL)
				break;
		}
		
		/*�ҵ��û�*/
		Customer customer = FinanceBll.getCustomerById(order.getCus_user_id());
		
		return new ReceiptTableData(receipt.getReceipt_id(), order.getOrder_id(), customer.getUser_id(), customer.getUser_name(), receipt.getReceipt_money()); 
	}
	
	/*���ݿͻ�ID�ҵ��վ�*/
	public static ReceiptTableData[] searchReceiptByCustomerId(String customerId) {
		if(customerId == "")	customerId = null;
		/*�ҵ�����ͻ�*/
		Customer customer = FinanceBll.getCustomerById(customerId);
		if(customer == null)	return null;
		/*�ҵ�����ͻ������ж���*/
		OrderedTableData[] orderTableData = FinanceBll.searchOrderByCustomerId(customerId);
		ReceiptTableData[] receiptTableData = new ReceiptTableData[orderTableData.length];
		for(int i = 0; i < receiptTableData.length; ++i) 
			receiptTableData[i] = FinanceBll.searchReceiptByOrderId(orderTableData[i].getOrderedId());
		
		return receiptTableData;
	}
	
	/*���ݿͻ��������ҵ��վ�*/
	public static ReceiptTableData[] searchReceiptByCustomerName(String customerName) {
		if(customerName == "")	customerName = null;
		Map<Integer, ReceiptTableData> ans = new HashMap<>();
		int ansNumber = 0;
		
		Map map = MapBuilder.buildMap("user_name", customerName);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList<Customer> arrayList = customerDao.findCustomer(map);
		int arraySize = arrayList.size();
		if(arraySize == 0)	return null;
		for(int i = 0; i < arraySize; ++i) {
			ReceiptTableData[] receiptTableData = FinanceBll.searchReceiptByCustomerId(arrayList.get(i).getUser_id());
			for(int j = 0; j < receiptTableData.length; ++j)
				ans.put(ansNumber++, receiptTableData[j]);
		}
		ReceiptTableData[] ansDatas = new ReceiptTableData[ansNumber];
		for(int i = 0; i < ansNumber; ++i) 
			ansDatas[i] = ans.get(i);
		
		return ansDatas;
	}
	
	
	/*================================================================�����ǳ��ɵ��߼�ҵ��ʵ��=====================================================*/
	/*
	 * �����վ�
	 * �վݱ�����ݣ�receiped_id, order_id, receipt_money, teller_user_id
	 * 			receipted_date
	 * �˵�������ݣ�bill_id, bill_date, bill_money, receipt_id, account_user_id
	 */
	public static void createReceipt(OrderedTableData data, String teller_user_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", data.getOrderedId());	
		map.put("receipt_money", data.getSum());
		map.put("teller_user_id", teller_user_id);
		String dateStr = getDate();
		map.put("receipted_date", dateStr);
		
		ReceiptDao dao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		dao.receiptByInsert(map);
	}
	
	/*
	 *���ݴ����Ķ���ID��������Ӧ�Ķ���
	 *����ֵΪOrderedTableData����������JTable����Ҫ����Ϣ
	 *OrderedTableData��������orderedId, customerId, customerName, type, sum
	 */
	public static OrderedTableData searchOrderByOrderId(String orderId) {
		if(orderId == "")	orderId = null;
		Ordered order = getOrderById(orderId);
		if(order == null)	return null;
		String customerId = order.getCus_user_id();
		int type = order.getOrder_type();
		int state = order.getOrder_state();
		
		//��ö����ܼ۵�һ��map
		Map<String, Object> getMoneyMap = new HashMap<>();
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		getMoneyMap.put("order_id", customerId);
		getMoneyMap.put("sum", 0);
		orderDao.selectOrder(getMoneyMap);
		float sum = (float)getMoneyMap.get("sum");
		
		//��ÿͻ�������һ��map
		Map<String, Object> getCustomerName = new HashMap<>();
		getCustomerName.put("user_id", customerId);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList<Customer> arrayList = customerDao.findCustomer(getCustomerName);
		if(arrayList.size() == 0)	return null;
		Customer customer = arrayList.get(0);	/*���û�ҵ��˿;ͷ���null*/
		String customerName = customer.getUser_name();

		
		return new OrderedTableData(orderId, customerId, customerName, type, state, sum);
		
	}
	
	/*
	 * ���ݿͻ���id��ѯ����
	 */
	public static OrderedTableData[] searchOrderByCustomerId(String cus_user_id) {
		if(cus_user_id == "")	cus_user_id = null;
		Map<String, Boolean> getOrderId = new HashMap<>();
		Map<Integer, String> order = new HashMap<>();
		int orderNum = 0;
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		Map<String, Object> getOrder = new HashMap<>();	//�����洢order
		getOrder.put("cus_user_id", cus_user_id);
		ArrayList<Ordered> arrayList = orderDao.findOrdered(getOrder);
		int listNum = arrayList.size();
		if(listNum == 0)	return null;
		for(int i = 0; i < listNum; ++i) {
			if(getOrderId.get(arrayList.get(i).getOrder_id()) == false) {
				getOrderId.put(arrayList.get(i).getOrder_id(), true);
				order.put(orderNum++, arrayList.get(i).getOrder_id());
			}
		}
		OrderedTableData[] orderedTableData = new OrderedTableData[orderNum];
		for(int i = 0; i < orderNum; ++i) {
			orderedTableData[i] = FinanceBll.searchOrderByOrderId(order.get(i));
		}
		return orderedTableData;
	}
	
	/*
	 * ���ݿͻ�������������Ӧ�Ķ���
	 */
	public static OrderedTableData[] searchOrderByCustomerName(String userName) {
		if(userName == "")	userName = null;
		Map<String, Object> getCustomerId = new HashMap<>();
		getCustomerId.put("user_name", userName);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList list = customerDao.findCustomer(getCustomerId);
		if(list.size() == 0)	return null;
		int customerNumber = list.size();	//�õ�������ͬ�����Ŀͻ�������
		
		
		Map<String, Boolean> getOrderId = new HashMap<>();
		Map<Integer, String> order = new HashMap<>();
		int orderNum = 0;
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		for(int i = 0; i < customerNumber; ++i) {	//ÿ��forѭ��������һ���ͻ������ж��������������޴�����Ҫͳ�������ж��ٸ�����.
			String cus_user_id = customerDao.findCustomer(getCustomerId).get(i).getUser_id();	//�õ���i���û���id��
			Map<String, Object> getOrder = new HashMap<>();		//�����洢order
			getOrder.put("cus_user_id", cus_user_id);			//�������Ĺؼ��ʷ���map
			ArrayList<Ordered> arrayList = orderDao.findOrdered(getOrder);	//�õ�order��ArrayList
			int listNum = arrayList.size();
			for(int j = 0; j < listNum; ++j) {
				if(getOrderId.get(arrayList.get(j).getOrder_id()) == false) {//������orderId��δ���ֹ��Ļ�
					getOrderId.put(arrayList.get(j).getOrder_id(), true);	//��¼�������Order��Id
					order.put(orderNum++, arrayList.get(j).getOrder_id());				//�����orderId����map��
				}
			}
		}
		//order���map���ڴ������з���Ҫ��Ķ�����������Ҫ�����ǳ�OrderTableData����
		OrderedTableData[] orderedTableData = new OrderedTableData[orderNum];
		for(int i = 0; i < orderNum; ++i) {
			orderedTableData[i] = FinanceBll.searchOrderByOrderId(order.get(i));
		}
		return orderedTableData;
	}
	//================================================================������һЩ�ڲ�����=====================================================
	/*
	 * ���ݶ�����ID��ȡ�����������Ϣ���������й���Ʒ����Ϣ��
	 */
	private static Ordered getOrderById(String orderId) {
		Map map = MapBuilder.buildMap("order_id", orderId);	//����һ��map
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		ArrayList<Ordered> arrayList = orderDao.findOrdered(map);
		if(arrayList.size() == 0)	return null;
		return arrayList.get(0);
	}
	
	private static Customer getCustomerById(String customerId) {
		Map map = MapBuilder.buildMap("cus_user_id", customerId);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList<Customer> arrayList = customerDao.findCustomer(map); 
		if(arrayList.size() == 0)	return null;
		return arrayList.get(0);
	}
	
	private static Receipt getReceiptById(String receiptId) {
		Map map = MapBuilder.buildMap("receipt_id", receiptId);
		ReceiptDao receiptDao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		ArrayList<Receipt> arrayList = receiptDao.findReceipt(map);
		if(arrayList.size() == 0)	return null;
		return arrayList.get(0);
	}
	
	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		return sdf.format(date);
	}
	
	private static String getDateA() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		date.setDate(date.getDate() + 3);
		return sdf.format(date);
	}
	
}
