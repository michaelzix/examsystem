package exam.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysRole;

public interface IRoleService {

	public abstract SysRole findUserById(Integer id);

	public abstract Page<SysRole> findUserList(String hql, Integer pageNum,
			Integer pageSize);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> checkValid(String value,
			String checkFieldName, Integer userId);

	public abstract ServerResponse<Object> createRole(SysRole role);

	public abstract ServerResponse<Object> updateRole(SysRole sysvo);
	
	public List<SysRole> getList(String hql);

}