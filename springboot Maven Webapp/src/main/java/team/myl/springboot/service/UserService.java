package team.myl.springboot.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import team.myl.springboot.model.User;

public interface UserService {

	String addUser(User user);

	PageInfo<User> findAllUser(int pageNum, int pageSize);

	int deletUser(String userName);

	User selectByPrimaryKey(int userId);

	int updateByPrimaryKey(User user);

	List<User> findAllUser2(int pageNum, int pageSize);

	Page<User> findByPage(int pageNo, int pageSize);
}
