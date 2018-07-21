package com.sduwh.foodcompany.comm;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.ibatis.annotations.Case;

import com.sduwh.foodcompany.entity.Administrators;
/*
 * @author 董喆
 * 检测字符串、日期等是否合法
 */
public class CheckUnit {
	 static Calendar calendar = Calendar.getInstance();//减少创建对象开销
	 /*
	  * @param mobilenumber 被检测的电话号码
	  * @return true为是电话号码 false为不是电话号码
	  */
	 public static boolean isMobileNumber(String mobilenumber) {
	       String val =  "^[1][3,4,5,7,8][0-9]{9}$";
	       boolean isPhone =Pattern.compile(val).matcher(mobilenumber).matches();
	       return isPhone;
	 }
	 /*
	  * @param name 一个姓名
	  * @return true为是中文姓名 false为不是中文姓名 
	  * 未测试
	  */
	 public static boolean ChineseNameTest(String name) {

	       String regex = "^[\\p{L} .'-]+$";

	       boolean isName = Pattern.matches(regex, name);
	       return isName;


	 }
	 /*
	  * @param name 年月日
	  * @return true为是合法年月日 false为不是合法年月日
	  * 未测试
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
	  * @param Value 一个可能包含中文的字符串
	  * @return 占位长度
	  * 未测试
	  */
	  public static int lengthText(String value) {
	       int valueLength = 0;
	       String chinese = "[\u0391-\uFFE5]";
	       /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
	       for (int i = 0; i < value.length(); i++) {
	           /* 获取一个字符 */
	           String temp = value.substring(i, i + 1);
	           /* 判断是否为中文字符 */
	           if (temp.matches(chinese)) {
	               /* 中文字符长度为2 */
	               valueLength += 2;
	           } else {
	               /* 其他字符长度为1 */
	               valueLength += 1;
	           }
	       }
	       return valueLength;
	   }
	  /*
		  * @param name 一个字符串
		  * @return 无
		  * 打印函数
		  */
	  public static void print(Object o) {
		  System.out.print(o);
	  }
	  /*
	   * 通过权限号得知管理员身份
	   */
	  public static String getPowerOfAdmin(int a) {
		  switch (a) {
		  	case Administrators.ACCOUNTANT_ADMIN_NUM:
		  		return "会计管理员";
		  	case Administrators.PRODUCE_ADMIN_NUM:
		  		return "生产车间管理员";
		  	case Administrators.PRODUCE_PLAN_ADMIN_NUM:
		  		return "生产计划科管理员";
		  	case Administrators.SALE_ADMIN_NUM:
		  		return "销售管理员";
		  	case Administrators.SYSTEM_ADMIN_NUM:
		  		return "系统管理员";
		  	case Administrators.TELLER_ADMIN_NUM:
		  		return "出纳管理员";
		  	case Administrators .WAREHOUSE_ADMIN_NUM:
		  		return "库房管理员";

		  }
		  return null;
	  }
}
