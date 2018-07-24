package com.sduwh.foodcompany.dao;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MybatisUtil;

public class DaoFactory {
	private String daoName;
	public final static String DAO_ADMINISTRATORS = "Administrators";
	public final static String DAO_BILL = "Bill";
	public final static String DAO_CUSTOMER = "CUSTOMER";
	public final static String DAO_GOODS = "GOODS";
	public final static String DAO_ORDERED = "Ordered";
	public final static String DAO_PICKUP = "PickUp";
	public final static String DAO_PRODUCEPLAN = "ProducePlan";
	public final static String DAO_RECEIPT = "Receipt";
	public final static String DAO_RETURNED = "Returned";
	public final static String DAO_RETURNING = "Returning";
	public final static String DAO_WAREHOUSE = "Warehouse";
	/*
	 * @param name dao接口的名字 
	 * @return Object dao接口
	 * Administrators
	 */
	public static Object createDao(String name){
		SqlSession session = MybatisUtil.getSession();
		switch (name) {
		case DAO_ADMINISTRATORS:
			return session.getMapper(AdministratorsDao.class);
		case DAO_BILL:
			return session.getMapper(BillDao.class);
		case DAO_CUSTOMER:
			return session.getMapper(CustomerDao.class);
		case DAO_GOODS:
			return session.getMapper(GoodsDao.class);
		case DAO_ORDERED:
			return session.getMapper(OrderedDao.class);
		case DAO_PICKUP:
			return session.getMapper(PickUpDao.class);
		case DAO_PRODUCEPLAN:
			return session.getMapper(ProducePlanDao.class);
		case DAO_RECEIPT:
			return session.getMapper(ReceiptDao.class);
		case DAO_RETURNED:
			return session.getMapper(ReturnedDao.class);
		case DAO_RETURNING:
			return session.getMapper(ReturningDao.class);
		case DAO_WAREHOUSE:
			return session.getMapper(WarehouseDao.class);
		default:
			return null;
		}
	}
}
