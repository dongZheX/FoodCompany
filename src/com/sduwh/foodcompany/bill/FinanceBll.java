package com.sduwh.foodcompany.bill;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.sql.rowset.CachedRowSet;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.*;
import com.sduwh.foodcompany.dao.*;
import com.sduwh.foodcompany.entity.*;


public class FinanceBll {
	/*================================================================以下是会计的逻辑业务实现=====================================================*/
	
	/*开具账单
	 * bill_id, bill_date, bill_money, receipt_id, accountant_user_id
	 */
	public static void createBill(ReceiptTableData receiptTableData, String accountantId) {
		Map<String, Object> map = new HashMap<>();
		//map.put("bill_date", getDate());
		map.put("money", receiptTableData.getMoney());
		map.put("receipt_id", receiptTableData.getReceiptId());
		map.put("accountant_user_id", accountantId);
		
		BillDao billDao = (BillDao)DaoFactory.createDao(DaoFactory.DAO_BILL);
		billDao.billByInsert(map);
		
		/*如果需要开具提货单的话，就开具提货单*/
		if(FinanceBll.needCreatePickUp(FinanceBll.searchOrderByOrderId(receiptTableData.getOrderId()))) {
			createPickUp(receiptTableData, accountantId);
		}
	}
	
	/*
	 * 开具提货单
	 * pick_up_id(就是order_id), pick_up_state, accountant_user_id
	 */
	private static void createPickUp(ReceiptTableData receiptTableData, String accountantId) {
		Map<String, Object> map = new HashMap<>();
		map.put("pick_up_id", receiptTableData.getOrderId());
		map.put("pick_up_state", PickUp.HAVE_NOT_PICKUP);
		map.put("accountant_user_id", accountantId);
		SqlSession session = MybatisUtil.getSession();
		PickUpDao pickUpDao = session.getMapper(PickUpDao.class);
		pickUpDao.insertPickUp(map);
		session.commit();
	}
	
	/*
	 * 判断是否需要开具提货单
	 */
	private static boolean needCreatePickUp(OrderedTableData data) {
		String type = data.getType();
		String state = data.getState();
		String CustomerId = data.getCustomerId();
		
		Map map = MapBuilder.buildMap("user_id", CustomerId);
		CustomerDao dao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		float score = dao.findCustomer(map).get(0).getCus_score();
		
		if		(score >= 80 && (type.equals("现货(后付)")	 	|| type.equals("预约(后付)")) && state.equals("已付定金"))	return true;
		else if	(score < 80  && (type.equals("现货(后付)") 	|| type.equals("预约(后付)")) && state.equals("已付尾款"))	return true;
		else if ((type.equals("现货(先付)") 					|| type.equals("预约(先付)")) && state.equals("已付尾款"))	return true;
		return false;
	}
	
	/*
	 * 根据收据Id找到收据
	 */
	public static ReceiptTableData[] searchReceiptByReceiptId(String receiptId) {
		if(receiptId == "")	receiptId = null;
		Map map = MapBuilder.buildMap("receipt_id", receiptId);
		ReceiptDao receiptDao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		ArrayList<Receipt> arrayList = receiptDao.findReceipt(map);
		if(arrayList.size() == 0)	return null;	/*如果数据库中没有该订单，则返回null*/
		
		
		ReceiptTableData[] data = new ReceiptTableData[arrayList.size()];
		int size = arrayList.size();
		for(int i = 0; i < size; ++i) {
			Receipt receipt = arrayList.get(i);
			Ordered order = FinanceBll.getOrderById(receipt.getOrder_id());
			Customer customer = FinanceBll.getCustomerById(order.getCus_user_id());
			data[i] = new ReceiptTableData(receipt.getReceipt_id(), order.getOrder_id(), customer.getUser_id(), customer.getUser_name(), receipt.getReceipt_money());
		}
		
		
		return data; 
	}
	
	/*
	 * 根据订单Id找到收据
	 */
	public static ReceiptTableData[] searchReceiptByOrderId(String orderId) {
		if(orderId == "")	orderId = null;
		Ordered order = FinanceBll.getOrderById(orderId);
		if(order == null)	return null;
		int orderState = order.getOrder_state();
		Map getReceipt = MapBuilder.buildMap("order_id", orderId);
		ReceiptDao receiptDao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		ArrayList<Receipt> arrayList = (ArrayList)receiptDao.findReceipt(getReceipt);
		if(arrayList.size() == 0)	return null;
		ReceiptTableData[]	data = new ReceiptTableData[arrayList.size()];
		/*通过for循环找到收据*/
		Receipt receipt = null;
		for(int i = 0; i < arrayList.size(); ++i) {
			receipt = arrayList.get(i);
			Customer customer = FinanceBll.getCustomerById(order.getCus_user_id());
			data[i] = new ReceiptTableData(receipt.getReceipt_id(), order.getOrder_id(), customer.getUser_id(), customer.getUser_name(), receipt.getReceipt_money()); 
		}
		
		/*找到用户*/
		
		
		return data;
	}
	
	/*根据客户ID找到收据*/
	public static ReceiptTableData[] searchReceiptByCustomerId(String customerId) {
		if(customerId == "")	customerId = null;
		/*找到这个客户*/
		Customer customer = FinanceBll.getCustomerById(customerId);
		if(customer == null)	return null;
		/*找到这个客户的所有订单*/
		OrderedTableData[] orderTableData = FinanceBll.searchOrderByCustomerId(customerId);
		ArrayList<ReceiptTableData> list = new ArrayList<ReceiptTableData>();
		ReceiptTableData[] receiptTableData;
		
		for(int i = 0; i < orderTableData.length; ++i) { 
			receiptTableData = FinanceBll.searchReceiptByOrderId(orderTableData[i].getOrderedId());
			for(int j = 0; j < receiptTableData.length; ++j)
				list.add(receiptTableData[j]);
		}
		receiptTableData = (ReceiptTableData[])list.toArray();
		return receiptTableData;
	}
	
	/*根据客户的姓名找到收据*/
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
	
	
	/*================================================================以下是出纳的逻辑业务实现=====================================================*/
	/*
	 * 开具收据
	 * 收据表的内容：receiped_id, order_id, receipt_money, teller_user_id
	 * 			receipted_date
	 * 账单表的内容：bill_id, bill_date, bill_money, receipt_id, account_user_id
	 */
	public static void createReceipt(OrderedTableData data, String teller_user_id) {
		
		char s = ' ';
		System.out.println("here");
		System.out.println(data.getState());
		switch(data.getState()){
		case "未付款": System.out.println(data.getType());if(data.getType().equals("预约(先付)") || data.getType().equals("现货(先付)"))
			s = 'B'; System.out.println("ssss");break;
		case "已付定金": s = 'A';System.out.println("ddd"); break;
		}
		
		System.out.println(s);
		
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", data.getOrderedId());	
		map.put("station",s+"" );
		map.put("bill_money", data.getSum());
		map.put("teller_user_id", teller_user_id);
		
		//String dateStr = getDate();
		//map.put("receipted_date", dateStr);
		
		ReceiptDao dao = (ReceiptDao)DaoFactory.createDao(DaoFactory.DAO_RECEIPT);
		dao.receiptByInsert(map);
		
	}
	
	/*
	 *根据传来的订单ID，查找相应的订单
	 *返回值为OrderedTableData对象，里面有JTable中需要的信息
	 *OrderedTableData的属性有orderedId, customerId, customerName, type, sum
	 */
	public static OrderedTableData searchOrderByOrderId(String orderId) {
		if(orderId == "")	orderId = null;
		Ordered order = getOrderById(orderId);
		if(order == null)	
		{
			System.out.println("无");
				return null;}

		String customerId = order.getCus_user_id();
		int type = order.getOrder_type();
		int state = order.getOrder_state();
		
		//获得订单总价的一个map
		Map<String, Object> getMoneyMap = new HashMap<>();
		
		SqlSession session = MybatisUtil.getSession();
		OrderedDao orderDao = session.getMapper(OrderedDao.class);
		getMoneyMap.put("order_id", orderId);
		getMoneyMap.put("sum", "0");
		orderDao.selectOrder(getMoneyMap);
		session.commit();
		
		float sum = (float)getMoneyMap.get("sum");
		//获得客户姓名的一个map
		Map<String, Object> getCustomerName = new HashMap<>();
		getCustomerName.put("user_id", customerId);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList<Customer> arrayList = customerDao.findCustomer(getCustomerName);
		if(arrayList.size() == 0)	return null;
		Customer customer = arrayList.get(0);	/*如果没找到顾客就返回null*/
		String customerName = customer.getUser_name();

		
		return new OrderedTableData(orderId, customerId, customerName, type, state, sum);
		
	}
	
	/*
	 * 根据客户的id查询订单
	 */
	public static OrderedTableData[] searchOrderByCustomerId(String cus_user_id) {
		if(cus_user_id == "")	cus_user_id = null;
		Map<String, Boolean> getOrderId = new HashMap<>();
		Map<Integer, String> order = new HashMap<>();
		int orderNum = 0;
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		Map<String, Object> getOrder = new HashMap<>();	//用来存储order
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
	 * 根据客户的姓名查找相应的订单
	 */
	public static OrderedTableData[] searchOrderByCustomerName(String userName) {
		if(userName == "")	userName = null;
		Map<String, Object> getCustomerId = new HashMap<>();
		getCustomerId.put("user_name", userName);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList list = customerDao.findCustomer(getCustomerId);
		if(list.size() == 0)	return null;
		int customerNumber = list.size();	//得到具有相同姓名的客户的数量
		
		
		Map<String, Boolean> getOrderId = new HashMap<>();
		Map<Integer, String> order = new HashMap<>();
		int orderNum = 0;
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		for(int i = 0; i < customerNumber; ++i) {	//每次for循环都是找一个客户的所有订单，订单数量巨大，所以要统计真正有多少个订单.
			String cus_user_id = customerDao.findCustomer(getCustomerId).get(i).getUser_id();	//得到第i个用户的id；
			Map<String, Object> getOrder = new HashMap<>();		//用来存储order
			getOrder.put("cus_user_id", cus_user_id);			//将搜索的关键词放入map
			ArrayList<Ordered> arrayList = orderDao.findOrdered(getOrder);	//得到order的ArrayList
			int listNum = arrayList.size();
			for(int j = 0; j < listNum; ++j) {
				String tempId = arrayList.get(j).getOrder_id();
				Boolean temp = getOrderId.get(tempId);
			
				if(temp == null) {//如果这个orderId从未出现过的话
					getOrderId.put(arrayList.get(j).getOrder_id(), true);	//记录下来这个Order的Id
					order.put(orderNum++, arrayList.get(j).getOrder_id());				//把这个orderId放在map里
				}
			}
		}
		//order这个map现在存着所有符合要求的订单，现在需要把它们成OrderTableData类型
		OrderedTableData[] orderedTableData = new OrderedTableData[orderNum];
		for(int i = 0; i < orderNum; ++i) {
			orderedTableData[i] = FinanceBll.searchOrderByOrderId(order.get(i));
		}
		return orderedTableData;
	}
	//================================================================以下是一些内部调用=====================================================
	/*
	 * 根据订单的ID获取订单的相关信息（不包括有关商品的信息）
	 */
	private static Ordered getOrderById(String orderId) {
		Map map = MapBuilder.buildMap("order_id", orderId);	//建立一个map
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
	
	public static String getDate() {
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
