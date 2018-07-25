package com.sduwh.foodcompany.bill;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.BillDao;
import com.sduwh.foodcompany.entity.Bill;

public class SelectBillBll {
	
	private static SqlSession session  = MybatisUtil.getSession();
	private static Calendar calendar = Calendar.getInstance();
	/*
	 * 通过账单时间查询
	 */
	public static ArrayList<Bill> select_bill_at_date(String year,String month,String day){
		//2018-01-01
		/*
		 * 查出所有的Bill，放入一个ArrayList<Bill>中
		 */
		System.out.println("year: "+year+"month: "+month+"day: "+day);
		BillDao dao = session.getMapper(BillDao.class);
		String [] key = {"bill_id",null,"bill_date",null,"bill_money",null,"receipt_id",null,"accountant_user_id",null};
		ArrayList<Bill> bill_list = dao.findBill(MapBuilder.buildMap(key));
		System.out.println(bill_list);
		/*
		 * 和日期比对
		 */
		ArrayList<Bill> bill_return = new ArrayList<Bill>();
		int count = 0;
		for(int i=0;i<bill_list.size();i++){
			Bill bill = bill_list.get(i);
			Date date = bill.getBill_date();
			calendar.setTime(date);
			if((day!=null&&day.equals(calendar.get(Calendar.DATE)+""))||day==null)count++;
			if((month!=null&&month.equals(calendar.get(Calendar.MONDAY)+1+""))||month==null)count++;
			if((year!=null&&year.equals(calendar.get(Calendar.YEAR)+""))||year==null)count++;
			System.out.println("date: "+calendar.get(Calendar.DATE)+"month: "+calendar.get(Calendar.MONDAY)+"year: "+calendar.get(Calendar.YEAR));
			if(count==3){
				bill_return.add(bill);
			}
			count = 0;
			
				
		}
		return bill_return;
	}
	
	/*
	 * 计算入账
	 */
	public static float in(ArrayList<Bill> bill_list){
		float money_tol = 0;
		for(int i=0;i<bill_list.size();i++){
			Bill bill = bill_list.get(i);
			float money = bill.getBill_money();
			if(money > 0)
				money_tol += money;
		}
		return money_tol;
	}
	
	/*
	 * 计算出账
	 */
	public static float out(ArrayList<Bill> bill_list){
		float money_tol = 0;
		for(int i=0;i<bill_list.size();i++){
			Bill bill = bill_list.get(i);
			float money = bill.getBill_money();
			if(money < 0)
				money_tol += money;
		}
		return money_tol;
	}
}
