package com.sduwh.foodcompany.entity;

import java.io.Serializable;
import java.sql.Date;
/*
 * @author ����
 * @date 2018/7/21
 * ӳ�����ݿ��е�ProducePlan��ʵ��toString��ʵ��equals
 */
public class ProducePlan implements Serializable{
	//δȷ��
	public static final int HAVE_NOT_CONFIRM = 1;
	//��Ͷ������
	public static final int HAVE_PUT_INTO_PRODUCE = 2;
	//���
	public static final int HAVE_FINISHED = 3;
	//ȡ��
	public static final int HAVE_CANCELED = 4;
	private String plan_id;
	private String good_id;
	private int good_num;
	private Date deadline;
	private int plan_state;
	private String planer_user_id;
	//Ĭ�Ϲ��캯��
	private ProducePlan() {
		plan_id = "";
		good_id = "";
		good_num = 0;
		deadline = null;
		plan_state = 0;
		planer_user_id = "";
	}
	//getters and setters
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public int getGood_num() {
		return good_num;
	}
	public void setGood_num(int good_num) {
		this.good_num = good_num;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getPlan_state() {
		return plan_state;
	}
	public void setPlan_state(int plan_state) {
		this.plan_state = plan_state;
	}
	public String getPlaner_user_id() {
		return planer_user_id;
	}
	public void setPlaner_user_id(String planer_user_id) {
		this.planer_user_id = planer_user_id;
	}
	@Override
	public String toString() {
		return "ProducePlan [plan_id=" + plan_id + ", good_id=" + good_id + ", good_num=" + good_num + ", deadline="
				+ deadline + ", plan_state=" + plan_state + ", planer_user_id=" + planer_user_id + "]";
	}
	public boolean equals(ProducePlan c) {
		if(c instanceof ProducePlan) {
			String id = c.getPlan_id();
			if(id.equals(c.getPlan_id())) {
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
	
	/*
	 * ����ת�ַ���
	 */
	public static String plan_state_toString(int plan_state_int){
		String plan_state_str = "";
		switch (plan_state_int) {
		case ProducePlan.HAVE_NOT_CONFIRM:
			plan_state_str = "δȷ��";
			break;
		case ProducePlan.HAVE_PUT_INTO_PRODUCE:
			plan_state_str = "��Ͷ������";
			break;
		case ProducePlan.HAVE_FINISHED:
			plan_state_str = "���";
			break;
		case ProducePlan.HAVE_CANCELED:
			plan_state_str = "ȡ��";
			break;
		default: plan_state_str =null;
			break;
		}
		return plan_state_str;
	}
	
	/*
	 * �ַ���ת����
	 */
	public static int plan_state_toInt(String plan_state_str){
		int plan_state_int = 0;
		switch (plan_state_str) {
		case "δȷ��":
			plan_state_int = ProducePlan.HAVE_NOT_CONFIRM;
			break;
		case "��Ͷ������":
			plan_state_int = ProducePlan.HAVE_PUT_INTO_PRODUCE;
			break;
		case "���":
			plan_state_int = ProducePlan.HAVE_FINISHED;
			break;
		case "ȡ��":
			plan_state_int = ProducePlan.HAVE_CANCELED;
			break;
		default: plan_state_int = -1;
			break;
		}
		return plan_state_int;
	}
}
