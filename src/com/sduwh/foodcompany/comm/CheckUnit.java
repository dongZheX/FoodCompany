package com.sduwh.foodcompany.comm;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.ibatis.annotations.Case;

import com.sduwh.foodcompany.entity.Administrators;
/*
 * @author ����
 * ����ַ��������ڵ��Ƿ�Ϸ�
 */
public class CheckUnit {
	 static Calendar calendar = Calendar.getInstance();//���ٴ���������
	 /*
	  * @param mobilenumber �����ĵ绰����
	  * @return trueΪ�ǵ绰���� falseΪ���ǵ绰����
	  */
	 public static boolean isMobileNumber(String mobilenumber) {
	       String val =  "^[1][3,4,5,7,8][0-9]{9}$";
	       boolean isPhone =Pattern.compile(val).matcher(mobilenumber).matches();
	       return isPhone;
	 }
	 /*
	  * @param name һ������
	  * @return trueΪ���������� falseΪ������������ 
	  * δ����
	  */
	 public static boolean ChineseNameTest(String name) {

	       String regex = "^[\\p{L} .'-]+$";

	       boolean isName = Pattern.matches(regex, name);
	       return isName;


	 }
	 /*
	  * @param name ������
	  * @return trueΪ�ǺϷ������� falseΪ���ǺϷ�������
	  * δ����
	  */
	 public static boolean isDay(String y,String m,String d) {
	       int year = Integer.parseInt(y);
	       int month = Integer.parseInt(m);
	       int day = Integer.parseInt(d);
	       int days[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	       if(year%400==0||(year%100!=0&&year%4==0)) {
	           days[2] = 29;
	       }
	       int presentYear = calendar.get(Calendar.YEAR);
	       return year>1900&&year<presentYear&&month<=12&&day<=days[month];
	   }
	 /*
	  * @param Value һ�����ܰ������ĵ��ַ���
	  * @return ռλ����
	  * δ����
	  */
	  public static int lengthText(String value) {
	       int valueLength = 0;
	       String chinese = "[\u0391-\uFFE5]";
	       /* ��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1 */
	       for (int i = 0; i < value.length(); i++) {
	           /* ��ȡһ���ַ� */
	           String temp = value.substring(i, i + 1);
	           /* �ж��Ƿ�Ϊ�����ַ� */
	           if (temp.matches(chinese)) {
	               /* �����ַ�����Ϊ2 */
	               valueLength += 2;
	           } else {
	               /* �����ַ�����Ϊ1 */
	               valueLength += 1;
	           }
	       }
	       return valueLength;
	   }
	  /*
		  * @param name һ���ַ���
		  * @return ��
		  * ��ӡ����
		  */
	  public static void print(Object o) {
		  System.out.print(o);
	  }
	  /*
	   * ͨ��Ȩ�޺ŵ�֪����Ա���
	   */
	  public static String getPowerOfAdmin(int a) {
		  switch (a) {
		  	case Administrators.ACCOUNTANT_ADMIN_NUM:
		  		return "��ƹ���Ա";
		  	case Administrators.PRODUCE_ADMIN_NUM:
		  		return "�����������Ա";
		  	case Administrators.PRODUCE_PLAN_ADMIN_NUM:
		  		return "�����ƻ��ƹ���Ա";
		  	case Administrators.SALE_ADMIN_NUM:
		  		return "���۹���Ա";
		  	case Administrators.SYSTEM_ADMIN_NUM:
		  		return "ϵͳ����Ա";
		  	case Administrators.TELLER_ADMIN_NUM:
		  		return "���ɹ���Ա";
		  	case Administrators .WAREHOUSE_ADMIN_NUM:
		  		return "�ⷿ����Ա";

		  }
		  return null;
	  }
	  /**
	   * ��ָ�����ڵ�λ�����������ڼ�ļ��
	   * 
	   * @param timeInterval
	   * @param date1
	   * @param date2
	   * @return
	   */
	  public static long dateDiff(String timeInterval, Date date1, Date date2) {
	   if (timeInterval.equals("year")) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date1);
	    int time = calendar.get(Calendar.YEAR);
	    calendar.setTime(date2);
	    return time - calendar.get(Calendar.YEAR);
	   }

	   if (timeInterval.equals("quarter")) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date1);
	    int time = calendar.get(Calendar.YEAR) * 4;
	    calendar.setTime(date2);
	    time -= calendar.get(Calendar.YEAR) * 4;
	    calendar.setTime(date1);
	    time += calendar.get(Calendar.MONTH) / 4;
	    calendar.setTime(date2);
	    return time - calendar.get(Calendar.MONTH) / 4;
	   }

	   if (timeInterval.equals("month")) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date1);
	    int time = calendar.get(Calendar.YEAR) * 12;
	    calendar.setTime(date2);
	    time -= calendar.get(Calendar.YEAR) * 12;
	    calendar.setTime(date1);
	    time += calendar.get(Calendar.MONTH);
	    calendar.setTime(date2);
	    return time - calendar.get(Calendar.MONTH);
	   }

	   if (timeInterval.equals("week")) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date1);
	    int time = calendar.get(Calendar.YEAR) * 52;
	    calendar.setTime(date2);
	    time -= calendar.get(Calendar.YEAR) * 52;
	    calendar.setTime(date1);
	    time += calendar.get(Calendar.WEEK_OF_YEAR);
	    calendar.setTime(date2);
	    return time - calendar.get(Calendar.WEEK_OF_YEAR);
	   }

	   if (timeInterval.equals("day")) {
	    long time = date1.getTime() / 1000 / 60 / 60 / 24;
	    return time - date2.getTime() / 1000 / 60 / 60 / 24;
	   }

	   if (timeInterval.equals("hour")) {
	    long time = date1.getTime() / 1000 / 60 / 60;
	    return time - date2.getTime() / 1000 / 60 / 60;
	   }

	   if (timeInterval.equals("minute")) {
	    long time = date1.getTime() / 1000 / 60;
	    return time - date2.getTime() / 1000 / 60;
	   }

	   if (timeInterval.equals("second")) {
	    long time = date1.getTime() / 1000;
	    return time - date2.getTime() / 1000;
	   }

	   return date1.getTime() - date2.getTime();
	  }
}
