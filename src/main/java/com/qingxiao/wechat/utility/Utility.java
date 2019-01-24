package com.qingxiao.wechat.utility;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Sjm
 * @date 2019-01-02
 */
public class Utility {
    private static final String token = "songjm";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 排序timestamp,nonce,token,进行加密后与signature比对
     * @param signature
     * @param timestamp
     * @param nonce
     * @return boolean
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        if("".equals(signature)||null==signature){
            return false;
        }
        String[] str = new String[]{token,timestamp,nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ;i<str.length;i++){
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }

    /**
     * 用于字符串SHA1加密并换成十六进制的字符串形式
     * @param str
     * @return String
     */
    private static String encode(String str) {
        if (str == null||"".equals(str)) {
            return null;
        }
        try {
            //生成实现指定摘要算法(SHA1)的 MessageDigest 对象
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            //使用指定的字节更新摘要
            messageDigest.update(str.getBytes());
            //通过运行诸如填充之类的终于操作完毕哈希计算。
            byte[] bytes =  messageDigest.digest();
            int len = bytes.length;
            StringBuilder buf = new StringBuilder(len * 2);
            // 把密文转换成十六进制的字符串形式
            for (int j = 0; j < len; j++) {
                buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
                buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
            }
            return buf.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
