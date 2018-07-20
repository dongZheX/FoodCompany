package com.sduwh.foodcompany.comm;

/**
 * Created by ASUS on 2018/7/20.
 * KMP工具类
 * @author 董喆
 */

public class KMP {
	/*
	 * @param str文本穿 sub模式串
	 * @return sub在 str中的位置
	 *   如果返回值为-1匹配失败
	 */
    public static int kmp(String str, String sub) {
        if(str == null || sub == null || str.length() == 0 || sub.length() == 0){
            throw new IllegalArgumentException("nothing");
        }

        int j = 0;
        int[] n = next(sub);
        for (int i = 0; i < str.length(); i++) {
            while(j > 0 && str.charAt(i) != sub.charAt(j)){
                j = n[j - 1];
            }

            if(str.charAt(i) == sub.charAt(j)){
                j++;
            }

            if(sub.length() == j){
                int index = i - j + 1;
                return index;
            }
        }

        return -1;
    }

    /**
     * 获取next
     * @param sub
     * @return
     */
    private static int[] next(String sub) {
        int[] n = new int[sub.length()];
        int x = 0;
        for (int i = 1; i < sub.length(); i++) {
            while (x > 0 && sub.charAt(x) != sub.charAt(x)) {
                x = n[x - 1];
            }

            if (sub.charAt(i) == sub.charAt(x)) {
                x++;
            }

            n[i] = x;
        }
        return n;
    }

}
