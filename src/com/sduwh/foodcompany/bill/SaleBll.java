package com.sduwh.foodcompany.bill;

import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.*;
import com.sduwh.foodcompany.entity.Customer;
import com.sduwh.foodcompany.entity.Goods;
import com.sduwh.foodcompany.entity.Warehouse;

public class SaleBll {
	/*客户注册*/
	public static void customerRegister(CustomerTableData customerTableData) {
		Map<String, Object> customerMap = new HashMap<>();
		customerMap.put("user_name", customerTableData.getCusName());
		customerMap.put("user_psw", customerTableData.getCusPsw());
		customerMap.put("user_tel", customerTableData.getCusTel());
		customerMap.put("cus_rank", customerTableData.getCusRank());
		customerMap.put("cus_score", customerTableData.getCusScore());
		customerMap.put("cus_address", customerTableData.getCusAddress());
		
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		customerDao.insertCustomer(customerMap);
	}
	
	/*根据顾客ID返回顾客信息*/
	public static Customer getCustomerById(String customerId) {
		Map map = MapBuilder.buildMap("cus_user_id", customerId);
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		return customerDao.findCustomer(map).get(0);
	}
	
	/*根据客户姓名返回客户信息*/
	public static Customer[] getCustomerByName(String customerName) {
		Map<String, String> customerMap = new HashMap<>();
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		ArrayList<Customer> arrayList = customerDao.findCustomer(customerMap);
		return (Customer[])arrayList.toArray();
	}
	
	/*修改客户信息*/
	public static void updateCustomer(String user_id, String user_tel, int cus_rank, float cus_score, String cus_address) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("user_tel", user_tel);
		map.put("cus_rank", cus_rank);
		map.put("cus_score", cus_score);
		map.put("cus_address", cus_address);
		
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		customerDao.updateCustomer(map);
	}
	
	/*显示所有的商品*/
	public static GoodsTableData[] getGoods() {
		/*private String good_id;
	private String good_name;
	private String good_standard;
	private float good_cost;
	private int good_expiration_date;*/
		
		ArrayList<Goods> goodsList = SelectGoodBll.select_good("good_id", null);
		int size = goodsList.size();
		GoodsTableData[] data = new GoodsTableData[size];
		for(int i = 0; i < size; ++i) {
			ArrayList<Warehouse> warehouseList = WarehouseService.getWarehouseList("good_id", goodsList.get(i).getGood_id());
			int sum = 0;
			for(int j = 0; j < warehouseList.size(); ++j)
				sum += warehouseList.get(j).getGood_num();
			data[i] = new GoodsTableData(0, sum, goodsList.get(i).getGood_id(), goodsList.get(i).getGood_name());
		}
		
		return data;
	}
	//goodsData, cus_user_id, sale_user_id, order_type, order_date, pick_up_time_start, pick_up_time_end, order_state
	public static void createOrder(GoodsTableData[] data, String cus_user_id, String sale_user_id, int order_type, String order_date, String pick_up_time_start,
			String pick_up_time_end, int order_state) {
		SqlSession session = MybatisUtil.getSession();
		OrderedDao dao = session.getMapper(OrderedDao.class);
		Map<String, Object> getID = new HashMap<>();
		getID.put("order_id", "0");
		dao.selectOrder(getID);
		session.commit();
		String order_id = (String)getID.get("order_id");
		OrderedDao orderDao = (OrderedDao)DaoFactory.createDao(DaoFactory.DAO_ORDERED);
		for(int i = 0; i < data.length; ++i) {
			Map<String, Object> map = new HashMap<>();
			map.put("order_id", order_id);
			map.put("good_id", data[i].getGoodsID());
			map.put("cus_user_id", cus_user_id);
			map.put("sale_user_id", sale_user_id);
			map.put("order_unit_price", data[i].getValue());
			map.put("order_num", data[i].getNeedNumber());
			map.put("order_type", order_type);
			map.put("order_date", order_date);
			map.put("pick_up_time_start", pick_up_time_start);
			map.put("pick_up_time_end", pick_up_time_end);
			map.put("order_state", order_state);
			orderDao.insertOrder(map);
		}
	}
	
	/*开具退货单*/
	//public void 
	
	
	public void cancelOrder() {
		/*order_id
good_id
cus_user_id
sale_user_id
order_unit_price
order_num
order_type
order_date
pick_up_time_start
pick_up_time_end
order_state
*/
		
		
	}
	
}
