package exam.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jfinal.plugin.activerecord.Page;

import exam.common.Constant;
import exam.common.ServerResponse;
import exam.model.SysUserInfo;
import exam.service.IUserInfoService;
public class UserInfoServiceImpl implements IUserInfoService  {
	private static final SysUserInfo dao = new SysUserInfo().dao();
	
	private static final String TABLENAME="sys_user_info";
		
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public SysUserInfo findUserInfoById(Integer id){
		SysUserInfo  role = dao.findById(id);
		if(role == null){
			role = new SysUserInfo();
		}
		return role;
	}


	@Override
	public Page<SysUserInfo> findUserInfoList(String hql,Integer pageNum,Integer pageSize){
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
		if(Constant.STUDENT_CODE.equals(checkFieldName)){
			SysUserInfo sysUserInfo = dao.findFirst(" select id from "+TABLENAME+" where student_code = ? "+hql, value);
			if(sysUserInfo != null){
				return ServerResponse.createByErrorMessage("学生编号已存在");
			}
		}
		return ServerResponse.createBySuccessMessage("校验通过");
	}
	

	@Override
	public ServerResponse<Object> createUserInfo(SysUserInfo sysUserInfo) {
		ServerResponse  validResponse= checkValid(sysUserInfo.getStudentCode(), Constant.STUDENT_CODE,sysUserInfo.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		sysUserInfo.setCreateTime(new Date());
		sysUserInfo.setUpdateTime(new Date());
		if(sysUserInfo.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updateSysUserInfo(SysUserInfo sysUserInfoVO) {
		ServerResponse  validResponse= checkValid(sysUserInfoVO.getStudentCode(), Constant.STUDENT_CODE,sysUserInfoVO.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		SysUserInfo sysUserInfo = findUserInfoById(sysUserInfoVO.getId());
		if(sysUserInfo != null){
			sysUserInfo.setAge(sysUserInfoVO.getId());
			sysUserInfo.setCnname(sysUserInfoVO.getCnname());
			sysUserInfo.setDepartment(sysUserInfoVO.getDepartment());
			sysUserInfo.setStudentCode(sysUserInfoVO.getStudentCode());
			sysUserInfo.setBirthday(sysUserInfoVO.getBirthday());
			sysUserInfo.setClassStr(sysUserInfoVO.getClassStr());
			sysUserInfo.setUpdateTime(new Date());
			if(sysUserInfo.update()) {
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("学生不存在");
		}
	}


	@Override
	public SysUserInfo findUserInfoByUserId(Integer userId) {
		logger.debug("select userInfo.* from {} userInfo inner join sys_user u on userInfo.sys_user_id=u.id where u.id={}",TABLENAME,userId);
		String sql = "select userInfo.* from "+TABLENAME+" userInfo inner join sys_user u on userInfo.sys_user_id=u.id where u.id=?";
		SysUserInfo sysUserInfo = dao.findFirst(sql, userId);
		if(sysUserInfo == null){
			sysUserInfo = new SysUserInfo();
		}
		return sysUserInfo;
	}
	
}
