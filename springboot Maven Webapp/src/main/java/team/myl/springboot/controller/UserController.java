package team.myl.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.myl.springboot.model.User;
import team.myl.springboot.service.UserService;

@Controller
@RequestMapping(value = "/user")
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
	@RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = { "application/json;charset=UTF-8" })
	public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

		return userService.findAllUser(pageNum, pageSize);
	}
}
