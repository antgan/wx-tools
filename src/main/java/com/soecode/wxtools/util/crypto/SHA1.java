package com.soecode.wxtools.util.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import com.soecode.wxtools.exception.AesException;

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
	
	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
			  {
		try {
			String[] array = new String[] { token, timestamp, nonce, encrypt };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
}
