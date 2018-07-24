package com.sduwh.foodcompany.bill;

import java.util.*;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.dao.*;
import com.sduwh.foodcompany.entity.Customer;
import com.sduwh.foodcompany.entity.Goods;

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
	public static void updateCustomer(String user_id, String user_tel, String cus_rank, String cus_score, String cus_address) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("user_tel", user_tel);
		map.put("cus_rank", cus_rank);
		map.put("cus_score", cus_score);
		map.put("cus_address", cus_address);
		
		CustomerDao customerDao = (CustomerDao)DaoFactory.createDao(DaoFactory.DAO_CUSTOMER);
		customerDao.updateCustomer(map);
	}
	
}
