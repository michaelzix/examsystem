package exam.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Page;

import exam.common.Constant;
import exam.common.ServerResponse;
import exam.model.SysRole;
import exam.model.SysUser;
import exam.service.IRoleService;
public class RoleServiceImpl implements IRoleService {
	private static final SysRole dao = new SysRole().dao();
	
	private static final String TABLENAME="sys_role";
		
	
	@Override
	public SysRole findUserById(Integer id){
		SysRole  role = dao.findById(id);
		if(role == null){
			role = new SysRole();
		}
		return role;
	}

	@Override
	public Page<SysRole> findUserList(String hql,Integer pageNum,Integer pageSize){
		return dao.paginate(pageNum,pageSize,"select * "," from "+TABLENAME+" where 1 = 1 " +StringUtils.defaultString(hql,"")+" order by create_time desc ");
	}

	

	
	@Override
	public ServerResponse deleteById(Integer id) {
		if(dao.deleteById(id)){
			return ServerResponse.createBySuccessMessage("删除成功");
		}else {
			return ServerResponse.createByErrorMessage("删除失败");
		}
	}
	
	
	@Override
	public ServerResponse<Object> checkValid(String value, String checkFieldName,Integer userId){
		String hql = "";
		if(userId != null){
			hql += " and id <> "+userId;
		}
		if(Constant.ROLENAME.equals(checkFieldName)){
			SysRole role = dao.findFirst(" select id from "+TABLENAME+" where name = ? "+hql, value);
			if(role != null){
				return ServerResponse.createByErrorMessage("角色名已存在");
			}
		}
		return ServerResponse.createBySuccessMessage("校验通过");
	}
	
	@Override
	public ServerResponse<Object> createRole(SysRole role) {
		ServerResponse  validResponse= checkValid(role.getName(), Constant.ROLENAME,role.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		if(role.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updateRole(SysRole sysvo) {
		ServerResponse  validResponse= checkValid(sysvo.getName(), Constant.ROLENAME,sysvo.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		SysRole role = findUserById(sysvo.getId());
		if(role != null){
			role.setName(sysvo.getName());
			role.setUpdateTime(new Date());
			if(role.update()) {
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("角色不存在");
		}
	}

	@Override
	public List<SysRole> getList(String hql) {
		// TODO Auto-generated method stub
		return dao.find("select * from "+TABLENAME+StringUtils.defaultString(hql, ""));
	}
	
}
