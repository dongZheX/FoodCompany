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
		case "Customer":
			return session.getMapper(CustomerDao.class);
		case "Goods":
			return session.getMapper(GoodsDao.class);
		case "Ordered":
			return session.getMapper(OrderedDao.class);
		case "PickUp":
			return session.getMapper(PickUpDao.class);
		case "ProducePlanDao":
			return session.getMapper(ProducePlanDao.class);
		case "Receipt":
			return session.getMapper(ReceiptDao.class);
		case "Returned":
			return session.getMapper(ReturnedDao.class);
		case "Returning":
			return session.getMapper(ReturningDao.class);
		case "Warehouse":
			return session.getMapper(WarehouseDao.class);
		default:
			return null;
		}
	}
}
