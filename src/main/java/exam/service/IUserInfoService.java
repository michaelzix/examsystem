package exam.service;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysUserInfo;

public interface IUserInfoService {

	public abstract SysUserInfo findUserInfoById(Integer id);

	public abstract Page<SysUserInfo> findUserInfoList(String hql,
			Integer pageNum, Integer pageSize);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> checkValid(String value,
			String checkFieldName, Integer userId);

	public abstract ServerResponse<Object> createUserInfo(
			SysUserInfo sysUserInfo);

	public abstract ServerResponse<Object> updateSysUserInfo(
			SysUserInfo sysUserInfoVO);
	
	public SysUserInfo findUserInfoByUserId(Integer userId);

}