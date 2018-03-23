package team.myl.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import team.myl.springboot.mapper.UserMapper;
import team.myl.springboot.model.User;
import team.myl.springboot.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;// 这里会报错，但是没事

	@Override
	public String addUser(User user) {
		try {
			userMapper.insertSelective(user);
		} catch (Exception e) {

		}
		return "ok";
	}

	/*
	 * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
	 * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可； pageNum 开始页数 pageSize
	 * 每页显示的数据条数
	 */
	@Override
	public List<User> findAllUser(int pageNum, int pageSize) {
		// 将参数传给这个方法就可以实现物理分页了，非常简单。
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectAllUser();
	}

	@Override
	public int deletUser(String userName) {
		int flag = 0;
		try {
			flag = userMapper.deleteByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User selectByPrimaryKey(int userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public int updateByPrimaryKey(User user) {
		int flag = 0;
		try {
			flag = userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
