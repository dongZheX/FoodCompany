package com.sduwh.foodcompany.entity;
/*
 * @author董喆 2018/7/21
 */
import java.io.Serializable;
/*
 * @author 董喆
 * @date 2018/7/20
 * 映射数据库中的Ordered表，实现toString，实现equals
 */
import java.sql.Date;
public class Ordered implements Serializable{
	//现货（先付）
	public static final int XIAN_HUO_BEF = 1;
	//现货（后附）
	public static final int XIAN_HUO_AFT = 2;
	//预约（先付）
	public static final int ORDER_HUO_BEF = 3;
	//预约（后付）
	public static final int ORDER_HUO_AFT = 4;
	//未付款
	public static final int UMPAID= 1;
	//付定金
	public static final int PAID_PART = 2;
	//付全款
	public static final int PAID_ALL = 3;
	//取消
	public static final int PAID_CANCEL = 4;
	private String order_id;
	private String good_id;
	private String cus_user_id;
	private String sale_user_id;
	private float order_unit_price;
	private int order_num;
	private int order_type;
	private Date pick_up_time_start;
	private Date pick_up_time_end ;
	private int order_state;
	//默认构造函数
	public Ordered() {
		order_id = "";
		good_id = "";
		cus_user_id = "";
		sale_user_id = "";
		order_unit_price = 0;
		order_num = 0;
		order_type = 0;
		pick_up_time_end = null;
		pick_up_time_start = null;
	}
	//getters and setters
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getCus_user_id() {
		return cus_user_id;
	}
	public void setCus_user_id(String cus_user_id) {
		this.cus_user_id = cus_user_id;
	}
	public String getSale_user_id() {
		return sale_user_id;
	}
	public void setSale_user_id(String sale_user_id) {
		this.sale_user_id = sale_user_id;
	}
	public float getOrder_unit_price() {
		return order_unit_price;
	}
	public void setOrder_unit_price(float order_unit_price) {
		this.order_unit_price = order_unit_price;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getOrder_type() {
		return order_type;
	}
	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}
	public Date getPick_up_time_start() {
		return pick_up_time_start;
	}
	public void setPick_up_time_start(Date pick_up_time_start) {
		this.pick_up_time_start = pick_up_time_start;
	}
	public Date getPick_up_time_end() {
		return pick_up_time_end;
	}
	public void setPick_up_time_end(Date pick_up_time_end) {
		this.pick_up_time_end = pick_up_time_end;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	@Override
	public String toString() {
		return "Ordered [order_id=" + order_id + ", good_id=" + good_id + ", cus_user_id=" + cus_user_id
				+ ", sale_user_id=" + sale_user_id + ", order_unit_price=" + order_unit_price + ", order_num="
				+ order_num + ", order_type=" + order_type + ", pick_up_time_start=" + pick_up_time_start
				+ ", pick_up_time_end=" + pick_up_time_end + ", order_state=" + order_state + "]";
	}
	public boolean equals(Ordered c) {
		if(c instanceof Ordered) {
			String id = c.getOrder_id();
			if(id.equals(getOrder_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
	
}
