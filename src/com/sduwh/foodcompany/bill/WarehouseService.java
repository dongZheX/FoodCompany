package com.sduwh.foodcompany.bill;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
	 * ���һ������¼
	 */
	public static void insertWarehouse(Object...args){
		//ArrayList<Warehouse> aList = new ArrayList<>();
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		Map<String,Object> map = MapBuilder.buildMap(args);
		dao.insertWarehouse(map);
	}
	/*
	 * ͨ��һϵ�в������һ������¼�б�
	 */
	public static ArrayList<Warehouse> getWarehouseList(Object...args){
		//ArrayList<Warehouse> aList = new ArrayList<>();
		OutOfDateRefresh();
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		Map<String,Object> map = MapBuilder.buildMap(args);
		return dao.findWareHouse(map);
	}
	/*
	 * @param name ��Ʒ����
	 */
	public static String findIdByGoodName(String name) {
		String id;
		GoodsDao dao = session.getMapper(GoodsDao.class);
		ArrayList<Goods> goods = dao.findGoods(MapBuilder.buildMap("good_name",name));
		if(goods.size()==0)
			return "";
		else
			id = goods.get(0).getGood_id();
		return id;
	}
	/*
	 * @param name ��Ʒ����
	 */
	public static String findNameByGoodId(String id) {
		String name;
		GoodsDao dao = session.getMapper(GoodsDao.class);
		ArrayList<Goods> goods = dao.findGoods(MapBuilder.buildMap("good_id",id));
		if(goods.size()==0)
			return "";
		else
			name = goods.get(0).getGood_name();
		return name;
	}
	/*
	 * @param id �û����
	 */
	public static String findNameByAdminId(String id) {
		String name;
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		ArrayList<Administrators> administrators = dao.findAdministrators(MapBuilder.buildMap("user_id",id));
		if(administrators.size()==0)
			return "";
		else
			name = administrators.get(0).getUser_name();
		return name;
	}
	/*
	 * @param name ����
	 */
	public static String findIdByAdminName(String name) {
		String id;
		AdministratorsDao dao = session.getMapper(AdministratorsDao.class);
		ArrayList<Administrators> administrators = dao.findAdministrators(MapBuilder.buildMap("user_name",name));
		if(administrators.size()==0)
			return "";
		else
			id = administrators.get(0).getUser_id();
		return id;
	}
	/*
	 * ���
	 * @param batch_id ���κţ�num ������� 
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
	 * ��ѯ�������Ʒ
	 */
	public static  ArrayList<Warehouse> findWareHouseOutOfDateAllMore(){
		OutOfDateRefresh();
		ArrayList<Warehouse> aList;
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		aList = dao.findWareHouseOutOfDateAllMore();
		return aList;
	}
	/*
	 * ˢ�¹�����Ʒ״̬
	 */
	public static void OutOfDateRefresh() {
		WarehouseDao dao = session.getMapper(WarehouseDao.class);
		dao.out_of_date_check();
		session.commit();
	}
	/*
	 * ������Ʒ�߼�
	 * @param batch_id
	 * @return falseû�й��ڲ������� true �Ѿ�����
	 */
	public static boolean destoryWarehouse(String batch_id) {
		WarehouseDao warehouseDao = session.getMapper(WarehouseDao.class);
		warehouseDao.updateWarehouse(MapBuilder.buildMap("batch_id",batch_id,"good_state",4));
		session.commit();
		return true;
		
	
	}
	/*
	 * @param warehouse ����¼�б� objects ��������
	 * �ⷿר�õ�tablemodel
	 */
	public static DefaultTableModel getTableModelForWareHouse(ArrayList<Warehouse> warehouses,Object[] objects) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(objects);
		for(int i = 0;i<warehouses.size();i++) {
			Warehouse warehouse = warehouses.get(i);
			String good_name = findNameByGoodId(warehouse.getGood_id());
			String warehouse_user = findNameByAdminId(warehouse.getWarehouse_user_id());
			String workshop_user = findNameByAdminId(warehouse.getWorkshop_user_id());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String good_PD = simpleDateFormat.format(warehouse.getGood_PD());
			String good_GP = simpleDateFormat.format(warehouse.getGood_GP());
			String state = Warehouse.numToState(warehouse.getGood_state());
			String data[] = {warehouse.getBatch_id(),good_name,warehouse.getGood_num()+"",good_PD,good_GP,warehouse_user,workshop_user,state};
			defaultTableModel.addRow(data);
		}
		if(warehouses.size()==0)
			defaultTableModel.setRowCount(22);
		return defaultTableModel;		
	}
	/*
	 * @param nothing
	 * ��ѯ������Ʒ
	 */
	public static ArrayList<Warehouse> getOutofDateWarehouse(){
		OutOfDateRefresh();
		WarehouseDao warehouseDao = session.getMapper(WarehouseDao.class);
		ArrayList<Warehouse> warehouses = warehouseDao.findWareHouse(MapBuilder.buildMap("good_state","3"));
		return warehouses;
	}
}
