package exam.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysUser;

public interface IUserService {
	
	Page<SysUser> findUserList(String hql,Integer pageNum,Integer pageSize);
	
	ServerResponse<Object> register(SysUser user);
	ServerResponse<Object> updateUser(SysUser user);

	ServerResponse login(String username,String pasword);
	
	SysUser findUserById(Integer id);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteByIds(Integer[] ids);
	
	List<SysUser> findUserList(String hql,Object ...pram);
	
}
