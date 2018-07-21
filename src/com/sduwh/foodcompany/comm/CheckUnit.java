package com.sduwh.foodcompany.comm;

import java.util.Calendar;
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
}
