package com.sduwh.foodcompany.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sduwh.foodcompany.entity.Customer;

public interface CustomerDao {
	/*
	 * �����ͻ�
	 */
	public void insertCustomer(Map map);
	/*
	 * ��ͨ����ֵuser_id,user_name,user_tel,cus_rank,cus_score,cus_add(ģ��)��ѯ
	 */
	public ArrayList<Customer> findCustomer(Map map);
	/*
	 * ���޸�user_tel,cus_rank,cus_score,cus_add����user_id
	 */
	public int updateCustomer(Map map);
}
