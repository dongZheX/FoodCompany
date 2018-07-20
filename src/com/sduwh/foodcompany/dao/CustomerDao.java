package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Customer;

public interface CustomerDao {
	/*
	 * 创建客户
	 */
	public void insertCustomer(Map map);
	/*
	 * 可通过键值user_id,user_name,user_tel,cus_rank,cus_score,cus_add(模糊)查询
	 */
	public ArrayList<Customer> findCustomer(Map map);
	/*
	 * 可修改user_tel,cus_rank,cus_score,cus_add根据user_id
	 */
	public int updateCustomer(Map map);
}
