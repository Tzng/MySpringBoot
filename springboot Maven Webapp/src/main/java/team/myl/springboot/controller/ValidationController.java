package team.myl.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.myl.springboot.model.SysUser;
import team.myl.springboot.service.SysUserService;
import team.myl.springboot.util.JwtToken;

@Controller
@RequestMapping(value = "/validation", produces = { "application/json;charset=UTF-8" })
public class ValidationController {
	protected static final String TOKEN_ERROR = "{\"state\":\"error\",\"mess\":\"tokenerror\"}";

	@Autowired
	private SysUserService sysUserService;

	@ResponseBody
	@RequestMapping(value = "/login")
	public String userLogin(String loginname, String password) throws Exception {
		JSONObject loginObj = new JSONObject();
		if (sysUserService.validateLogin(loginname, password)) {
			SysUser user = sysUserService.getUserByLoginname(loginname);
			String token = JwtToken.createToken(user);
			loginObj.put("token", token);
			loginObj.put("status", "1");
			loginObj.put("mesg", "");
			return loginObj.toJSONString();
		} else {
			loginObj.put("status", "2");
			loginObj.put("mesg", "2");
			return loginObj.toJSONString();
		}
	}

/*	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity handle() {
		return new ResponseEntity(HttpStatus.OK);
	}
*/
	@RequestMapping(value = "/login.html")
	public String toLogin() throws Exception {
		return "/index.html";
	}
}
