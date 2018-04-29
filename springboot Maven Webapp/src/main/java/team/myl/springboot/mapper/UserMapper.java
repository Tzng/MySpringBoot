package team.myl.springboot.mapper;

import java.util.List;

import com.github.pagehelper.Page;

import team.myl.springboot.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	int deleteByUserName(String userName);

	List<User> selectAllUser();

	Page<User> findByPage();
}