package com.soecode.wxtools.util;

/**
 * 随机字符串工具
 * @author antgan
 *
 */
public class RandomUtils {

	private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final java.util.Random RANDOM = new java.util.Random();

	/**
	 * 生成随机字符串
	 * @param num 位数 
	 * @return
	 */
	public static String getRandomStr(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
		}
		return sb.toString();
	}

}
