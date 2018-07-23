package com.sduwh.foodcompany.bill;
import java.util.ArrayList;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.OrderedDao;
import com.sduwh.foodcompany.entity.Ordered;

public class SelectOrderedBll {
	
	private static SqlSession session = MybatisUtil.getSession();
	
	
	/*
	 * 通过order_id查order实体
	 */
	public static ArrayList<Ordered> select_ordered(Object...args){
		OrderedDao dao = session.getMapper(OrderedDao.class);
		Map map = MapBuilder.buildMap(args);
		return dao.findOrdered(map);
	}
}
