package com.sduwh.foodcompany.bill;

import java.util.ArrayList;

import javax.mail.Session;
import javax.net.ssl.SSLContext;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.DaoFactory;
import com.sduwh.foodcompany.entity.Administrators;

public class PersonalInfoBll {
	/*
	 * �޸������߼�
	 * @param id �û�id tel �û��µ绰
	 */
	public static boolean reSetUserTel(String id,String tel){
		SqlSession sqlSession = MybatisUtil.getSession();
		AdministratorsDao dao = sqlSession.getMapper(AdministratorsDao.class);
		//����ֵ��ȷ��
		int result;
		try {
		    result = dao.updateAdministrators(MapBuilder.buildMap("user_id",id,"user_tel",tel));
		}catch (Exception e) {
			return false;
		}
		sqlSession.commit();
		sqlSession.close();
		if(result>0)
			return true;
		else
			return false;		
	}
	/*
	 * �޸������߼�
	 */
	public static boolean reSetPwd(String id,String opas,String npass) {
		SqlSession sqlSession = MybatisUtil.getSession();
		AdministratorsDao dao = sqlSession.getMapper(AdministratorsDao.class);
		String oldtruepass = dao.findAdministrators(MapBuilder.buildMap("user_id",id)).get(0).getUser_psw();
		String MD5opass = MD5.getMD5(opas);
		if(!MD5opass.equals(oldtruepass)) {
			return false;
		}else {
			
				dao.updateAdministrators(MapBuilder.buildMap("user_id",id,"user_psw",MD5.getMD5(npass)));
				sqlSession.commit();
				sqlSession.close();
				return true;
			
		}		
	}
	/*
	 * ��ȡͨѶ¼
	 */
	public static ArrayList getAdminList() {
		SqlSession sqlSession = MybatisUtil.getSession();
		AdministratorsDao dao  = sqlSession.getMapper(AdministratorsDao.class);
		return dao.findAdministrators(MapBuilder.buildMap("",""));
	}
}
