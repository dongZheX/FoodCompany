package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.CustomerDao;
import com.sduwh.foodcompany.dao.GoodsDao;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Customer;
import com.sduwh.foodcompany.entity.Goods;

public class IdToName {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	/*
	 * 通过管理员id返回管理员用户姓名
	 * @param adm_user_id 管理员编号
	 */
	public static String Administrators_Select(String adm_user_id){
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", adm_user_id);
		ArrayList<Administrators> adm_list = dao.findAdministrators(map);
		if(adm_list.size() == 0) return "";
		else return adm_list.get(0).getUser_name();
	}
	/*
	 * 通过客户id返回客户姓名
	 * @param cus_user_id 客户编号
	 */
	public static String Customer_select(String cus_user_id){
		CustomerDao dao = session.getMapper(CustomerDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", cus_user_id);
		ArrayList<Customer> cus_list = dao.findCustomer(map);
		if(cus_list.size() == 0) return "";
		else return cus_list.get(0).getUser_name();
	}
	/*
	 * 通过商品id返回商品名
	 * @param good_id 商品编号
	 */
	public static String Goods_select(String good_id){
		GoodsDao dao = session.getMapper(GoodsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("good_id", good_id);
		ArrayList<Goods> good_list = dao.findGoods(map);
		if(good_list.size() == 0) return "";
		else return good_list.get(0).getGood_name();
	}
}
