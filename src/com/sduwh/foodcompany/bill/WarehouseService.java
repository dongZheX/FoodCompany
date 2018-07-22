package com.sduwh.foodcompany.bill;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.Attributes.Name;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.GoodsDao;
import com.sduwh.foodcompany.dao.WarehouseDao;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Goods;
import com.sduwh.foodcompany.entity.Warehouse;
/*
 * create by dongzhe serve for warehouse
 */
public class WarehouseService {
	/*
	 * getSesson
	 */
	private static SqlSession session = MybatisUtil.getSession();
	/*
	 * 通过一系列参数获得一个库存记录列表
	 */
	public ArrayList<Warehouse> getWarehouseList(Object...args){
		ArrayList<Warehouse> aList;
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		aList = dao.findWareHouse(MapBuilder.buildMap(args));
		CheckUnit.print(aList);
		return aList;
	}
	/*
	 * @param name 商品名称
	 */
	public static String findIdByGoodName(String name) {
		String id;
		GoodsDao dao = session.getMapper(GoodsDao.class);
		ArrayList<Goods> goods = dao.findGoods(MapBuilder.buildMap("good_name",name));
		id = goods.get(0).getGood_id();
		return id;
	}
	/*
	 * @param id 用户编号
	 */
	public static String findNameByAdminId(String id) {
		String name;
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		ArrayList<Administrators> administrators = dao.findAdministrators(MapBuilder.buildMap("User_id",id));
		name = administrators.get(0).getUser_name();
		return name;
	}
	/*
	 * 提货
	 * @param batch_id 批次号，num 提货数量 
	 */
	public static boolean pickUpBatch(String batch_id,int num) {
		WarehouseDao warehouseDao = session.getMapper(WarehouseDao.class);
		int temp;
		ArrayList<Warehouse> warehouses = warehouseDao.findWareHouse(MapBuilder.buildMap("batch_id",batch_id));
        temp = warehouses.get(0).getGood_num();	
        if(temp-num==0)
        {
        	warehouseDao.updateWarehouse(MapBuilder.buildMap("batch_id",batch_id,"good_num",temp-num,"good_state",Warehouse.WAREHOUSE_EMPTY));
        	session.commit();
        	return true;
        }
        else
        {
        	warehouseDao.updateWarehouse(MapBuilder.buildMap("batch_id",batch_id,"good_num",temp-num));
        	session.commit();
        	return true;
        }		
	}
	/*
	 * 查询快过期商品
	 */
	public static  ArrayList<Warehouse> getWarehouseOutOfDateList(Object...args){
		ArrayList<Warehouse> aList;
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		aList = dao.findWareHouseOutOfDateAllMore();
		CheckUnit.print(aList);
		return aList;
	}
	/*
	 * 刷新过期商品状态
	 */
	public static void OutOfDateRefresh() {
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		dao.out_of_date_check();
		session.commit();
	}
	/*
	 * 销毁商品逻辑
	 * @param batch_id
	 * @return false没有过期不能销毁 true 已经销毁
	 */
	public static boolean destoryWarehouse(String batch_id) {
		WarehouseDao warehouseDao = session.getMapper(WarehouseDao.class);
		ArrayList<Warehouse> warehouses = warehouseDao.findWareHouse(MapBuilder.buildMap("batch_id",batch_id));
		Date date = warehouses.get(0).getGood_GP();
		if(CheckUnit.dateDiff("day", date, new Date())>0) {
			warehouseDao.updateWarehouse(MapBuilder.buildMap("batch_id",batch_id,"good_state",4));
			session.commit();
			return true;
		}else {
			return false;
		}
	
	}
	/*
	 * @param warehouse 库存记录列表 objects 这是列名
	 * 库房专用的tablemodel
	 */
	public static DefaultTableModel getTableModelForWareHouse(ArrayList<Warehouse> warehouses,Object[] objects) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(objects);
		for(int i = 0;i<warehouses.size();i++) {
			Warehouse warehouse = warehouses.get(i);
			String good_name = findIdByGoodName(warehouse.getGood_id());
			String warehouse_user = findNameByAdminId(warehouse.getWarehouse_user_id());
			String workshop_user = findNameByAdminId(warehouse.getWorkshop_user_id());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String good_PD = simpleDateFormat.format(warehouse.getGood_PD());
			String good_GP = simpleDateFormat.format(warehouse.getGood_GP());
			String state = Warehouse.numToState(warehouse.getGood_state());
			String data[] = {warehouse.getBatch_id(),good_name,warehouse.getGood_num()+"",good_PD,good_GP,state};
		}
		return defaultTableModel;
		
	}
}
