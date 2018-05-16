package team.myl.springboot.mapper;

import org.apache.ibatis.annotations.Param;

import team.myl.springboot.model.SysUser;

public interface SysUserMapper {
	int deleteByPrimaryKey(String uid);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String uid);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	/**
	 * 这里需要和XML中的相对应 ，注解的方式，注入参数
	 */
	SysUser loginValidate(@Param("loginName") String loginName, @Param("passWord") String passWord);

	SysUser selectByLoginname(@Param("loginName") String loginName);
}