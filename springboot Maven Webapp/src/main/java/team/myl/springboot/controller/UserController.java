
package team.myl.springboot.controller;

import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import team.myl.springboot.model.User;
import team.myl.springboot.service.UserService;

/**
 * 在控制器中接收参数的方法查看这个链接 https://www.cnblogs.com/wxwBlog/p/6128882.html
 * 
 * @author Allahbin
 *
 */
@Controller
@RequestMapping(value = "/user", produces = { "application/json;charset=UTF-8" })
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/add", produces = { "application/json;charset=UTF-8" })
	public String addUser() {
		User user = new User();
		user.setUserId(12311);
		user.setPassword("123");
		user.setPhone("15422345");
		user.setUserName("张三");
		return userService.addUser(user);
	}

	@ResponseBody
	@RequestMapping(value = "/all/{pageNum}/{pageSize}")
	public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

		return userService.findAllUser(pageNum, pageSize);
	}

	/**
	 * 接收参数的方式1 ：http://localhost:8080/delet/张三
	 */
	@ResponseBody
	@RequestMapping(value = "/delet/{userName}", produces = { "application/json;charset=UTF-8" })
	public int deletUser(@PathVariable("userName") String userName) {
		int flag = userService.deletUser(userName);
		return flag;
	}

	/**
	 * 1.接收参数的方式2 ； 测试路径：http://localhost:8080/user/updata?userId=1&phone=2
	 */
	@ResponseBody
	@RequestMapping(value = "/updata")
	public int updataUser(int userId, String phone) {
		User user = userService.selectByPrimaryKey(userId);
		user.setPhone(phone);
		int flag = userService.updateByPrimaryKey(user);
		return flag;
	}

	/**
	 * 接收参数的方式3，测试中
	 */
	@ResponseBody
	@RequestMapping(value = "/updata2", method = RequestMethod.POST)
	public int updataUser2(@RequestBody Map<String, Object> reqMap) {
		int userId = (int) reqMap.get("userId");
		String phone = reqMap.get("phone").toString();
		User user = userService.selectByPrimaryKey(userId);
		user.setPhone(phone);
		int flag = userService.updateByPrimaryKey(user);
		return flag;
	}

	/**
	 * 1.直接把表单的参数写在Controller相应的方法的形参中；
	 * 测试路径：http://localhost:8080/user/addUser1?username=1&password=2
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/addUser1")
	public String addUser1(String username, String password) {
		System.out.println("username is:" + username);
		System.out.println("password is:" + password);
		return "ok";
	}

	/**
	 * 2、通过HttpServletRequest接收 测试路径
	 * http://localhost:8080/user/addUser2?username=1&password=222
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser2")
	public String addUser2(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username is:" + username);
		System.out.println("password is:" + password);
		return "{\"state\":\"OK\"}";
	}

	/**
	 * 3、通过一个bean来接收 这个有错~，主要是不知道怎么传递一个bean进来
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser3")
	public String addUser3(User user) {
		System.out.println("username is:" + user.getUserName());
		System.out.println("password is:" + user.getPassword());
		return "{\"state\":\"OK\"}";
	}

	/**
	 * 4、使用@ModelAttribute注解获取POST请求的FORM表单数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser5", method = RequestMethod.POST)
	public String addUser5(@ModelAttribute("user") User user) {
		System.out.println("username is:" + user.getUserName());
		System.out.println("password is:" + user.getPassword());
		return "{\"state\":\"OK\"}";
	}
}
