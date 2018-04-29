
package team.myl.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import team.myl.springboot.service.UserService;

/**
 * 在控制器中接收参数的方法查看这个链接 https://www.cnblogs.com/wxwBlog/p/6128882.html
 * 
 * @author Allahbin
 *
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/myhtml")
	public String addUser() {
		return "/MyHtml";
	}
}
