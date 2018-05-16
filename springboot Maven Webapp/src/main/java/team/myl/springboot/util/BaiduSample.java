package team.myl.springboot.util;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class BaiduSample {
	// 设置APPID/AK/SK
	public static final String APP_ID = "";
	public static final String API_KEY = "";
	public static final String SECRET_KEY = "";
	static AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

	public static void main(String[] args) {
		// 初始化一个AipNlp
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		// System.setProperty("aip.log4j.conf",
		// "path/to/your/log4j.properties");

		// 调用接口
		// String text = "百度是一家高科技公司";
		// JSONObject res = client.lexer(text, null);
		// System.out.println(res.toString(2));

	}

	public String clientStr(String text) {
		JSONObject res = client.lexer(text, null);
		return res.toString();
	};
}