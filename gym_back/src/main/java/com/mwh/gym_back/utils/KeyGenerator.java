package com.mwh.gym_back.utils;

import java.security.SecureRandom;
import java.util.Base64;

//用于生成密钥
public class KeyGenerator {
    public static void main(String[] args) {
        // 创建一个 256 位的随机密钥
        byte[] key = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);

        // 使用 Base64 编码将字节数组转换为字符串
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println(base64Key);
    }
}
