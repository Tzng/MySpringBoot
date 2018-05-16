package team.myl.springboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralTools {
	/**
	 * 特殊字符验证
	 * 
	 * @param str
	 *        待验证的字符
	 * @return boolean 有特殊字符返回true，否则false
	 */
	public boolean isSpecialChar(String... str) {
		boolean strFlag = false;

		for (String strboolean : str) {
			// 如果属于敏感字符，则跳出循环
			if (strFlag) {
				break;
			}
			// 进行字符串特殊字符验证
			String regEx = "[~#$%^&*<>|\\/{}\\[\\]]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(strboolean);
			if (m.find()) {
				strFlag = true;
				break;
			}
		}
		return strFlag;
	}

	/*
	 * 方法二：推荐，速度最快 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */

	public static boolean isInteger(String str) {
		if (str != null) {
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
			return pattern.matcher(str).matches();
		} else {
			return false;
		}
	}
}
