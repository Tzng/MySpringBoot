package team.myl.springboot.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import team.myl.springboot.model.SysUser;

public class JwtToken {

	private static final String SECRET = "h8t^$r1%qK8rvmbUm5453VR#H1sMWLTj!#neA39";

	/*
	 * public static String createToken() throws Exception { Map<String, Object>
	 * map = new HashMap<String, Object>(); map.put("alg", "HS256");
	 * map.put("typ", "JWT"); String token = JWT.create().withHeader(map)//
	 * header .withClaim("name", "zwz")// payload .withClaim("age",
	 * "18").sign(Algorithm.HMAC256("secret"));// 加密 return token; }
	 */
	public static String createToken(SysUser user) throws Exception {
		// 签发时间
		Date iatDate = new Date();

		// 过期时间
		Calendar nowTime = Calendar.getInstance();
		// 设置成两个月
		nowTime.add(Calendar.MONTH, 2);
		Date expiresDate = nowTime.getTime();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token = "";
		try {
			token = JWT.create().withHeader(map)// header
					.withClaim("name", user.getUsername()).withClaim("userType", user.getUsertype())
					.withClaim("loginame", user.getLoginname()).withExpiresAt(expiresDate).withIssuedAt(iatDate)
					.sign(Algorithm.HMAC256(SECRET));// 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public static Map<String, Claim> verifyToken(String token) throws Exception {
		JWTVerifier verfier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verfier.verify(token);
		} catch (Exception e) {
			throw new RuntimeException("登录过期，请重新登录");
		}
		return jwt.getClaims();
	}
}
