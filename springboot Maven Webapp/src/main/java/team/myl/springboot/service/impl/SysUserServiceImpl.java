package team.myl.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.myl.springboot.mapper.SysUserMapper;
import team.myl.springboot.model.SysUser;
import team.myl.springboot.service.SysUserService;

@Service(value = "SysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public Boolean addUser(SysUser sysUser) {
		if (sysUserMapper.insert(sysUser) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean deletUser(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(SysUser sysUser) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean validateLogin(String loginname, String password) {
		// 用户名密码检测
		if (sysUserMapper.loginValidate(loginname, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SysUser getUserByLoginname(String loginname) {
		SysUser user = sysUserMapper.selectByLoginname(loginname);
		return user;
	}

}
