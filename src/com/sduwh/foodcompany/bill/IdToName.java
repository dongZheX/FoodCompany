package com.sduwh.foodcompany.bill;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.CustomerDao;
import com.sduwh.foodcompany.dao.GoodsDao;

public class IdToName {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	/*
	 * ͨ������Աid���ع���Ա�û�����
	 * @param adm_user_id ����Ա���
	 */
	public static String Administrators_Select(String adm_user_id){
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", adm_user_id);
		return dao.findAdministrators(map).get(0).getUser_name();
	}
	/*
	 * ͨ���ͻ�id���ؿͻ�����
	 * @param cus_user_id �ͻ����
	 */
	public static String Customer_select(String cus_user_id){
		CustomerDao dao = session.getMapper(CustomerDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", cus_user_id);
		return dao.findCustomer(map).get(0).getUser_name();
	}
	/*
	 * ͨ����Ʒid������Ʒ��
	 * @param good_id ��Ʒ���
	 */
	public static String Goods_select(String good_id){
		GoodsDao dao = session.getMapper(GoodsDao.class);
		Map<String, Object> map = new HashMap<>();
		map.put("good_id", good_id);
		return dao.findGoods(map).get(0).getGood_name();
	}
}
