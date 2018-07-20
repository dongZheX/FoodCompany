package com.sduwh.foodcompany.comm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ASUS on 2018/4/28.
 * @author 董喆
 * 取得MD5 加密数据库用户密码
 */

public class MD5 {
	/*
	 * @param content 需要加密的内容
	 * @return 返回加密后的内容
	 */
    public static String getMD5(String content){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
	 * @param digest
	 */
    private static String getHashString(MessageDigest digest){
        StringBuilder builder = new StringBuilder();
        for(byte b:digest.digest()){
            builder.append(Integer.toHexString((b>>4)&0xf));
            builder.append(Integer.toHexString(b&0xf));

        }
        return builder.toString();
    }
}
