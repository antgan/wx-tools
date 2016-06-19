package com.soecode.wxtools.util.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * SHA1 
 * @author
 */
public class SHA1 {

	/**
	 * 串接arr参数，生成sha1 digest
	 *
	 * @param arr
	 * @return
	 */
	public static String gen(String token, String timestamp,String nonce) throws NoSuchAlgorithmException {
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuffer content = new StringBuffer();
		for (String a : arr) {
			content.append(a);
		}
		return DigestUtils.shaHex(content.toString());
	}

	/**
	 * 用&串接arr参数，生成sha1 digest
	 *
	 * @param arr
	 * @return
	 */
	public static String genWithAmple(String... arr) throws NoSuchAlgorithmException {
		Arrays.sort(arr);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			String a = arr[i];
			sb.append(a+"&");
		}
		String string1 = sb.toString().substring(0, sb.length()-1);
		return DigestUtils.shaHex(string1);
	}
	
}
