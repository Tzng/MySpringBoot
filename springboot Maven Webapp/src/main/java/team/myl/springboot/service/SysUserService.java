package team.myl.springboot.service;

import team.myl.springboot.model.SysUser;

public interface SysUserService {

	Boolean addUser(SysUser sysUser);

	Boolean deletUser(String uid);

	int updateByPrimaryKey(SysUser sysUser);

	Boolean validateLogin(String loginname, String password);

	SysUser getUserByLoginname(String loginname);
}
