package team.myl.springboot.service;

import java.util.List;

import team.myl.springboot.model.User;

public interface UserService {

	String addUser(User user);

	List<User> findAllUser(int pageNum, int pageSize);

}
