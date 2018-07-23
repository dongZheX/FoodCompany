package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.GoodsDao;
import com.sduwh.foodcompany.entity.Goods;

public class SelectGoodBll {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	
	/*
	 * 通过good_id,good_name查询商品实体，返回arraylist<Goods>
	 */
	public static ArrayList<Goods> select_good(Object...args){
		GoodsDao dao = session.getMapper(GoodsDao.class);
		Map map = MapBuilder.buildMap(args);
		return dao.findGoods(map);
	}
}
