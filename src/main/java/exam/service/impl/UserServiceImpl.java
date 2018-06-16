package exam.service.impl;
import java.util.Date;
import java.util.List;
import com.jfinal.plugin.activerecord.Page;
import exam.common.Constant;
import exam.common.ServerResponse;
import exam.model.SysUser;
import exam.service.IUserService;
import exam.utils.EncryptUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class UserServiceImpl  implements IUserService {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private static final SysUser dao = new SysUser().dao();

	private static final String TABLENAME="sys_user";

	public SysUser findUserById(Integer id){
		SysUser user = dao.findById(id);
		if(user == null){
			user = new SysUser();
		}
		return user;
	}

	public List<SysUser> findUserList(String hql,Object ...pram){
		return  dao.find("select * from "+TABLENAME+" where status = ? " +StringUtils.defaultString(hql, "")+" order by t.id ","启用",pram);
	}
	
	
	public Page<SysUser> findUserList(String hql,Integer pageNum,Integer pageSize){
		return dao.paginate(pageNum,pageSize,"select * "," from "+TABLENAME+" where 1 = 1 " +StringUtils.defaultString(hql,"")+" order by create_time desc ");
	}
	
	public ServerResponse login(String username,String pasword){
		if(logger.isDebugEnabled()){
			logger.debug("select * from {} where username= {} and password = {} and status = {}" , TABLENAME, username, pasword, "启用");
		}
		SysUser user = dao.findFirst(" select * from "+TABLENAME+" where username= ? and password = ? and status = ? ",username,pasword,"启用");
		if(user == null || user.isNew()){
			return ServerResponse.createByErrorMessage("用户或密码错误");
		}else{
			return ServerResponse.createBySuccess("登录成功", user);
		}
	}

	@Override
	public ServerResponse register(SysUser user) {
		// TODO Auto-generated method stub
		ServerResponse  validResponse= checkValid(user.getUsername(), Constant.USERNAME,user.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		validResponse = checkValid(user.getEmail(), Constant.EMAIL,user.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		validResponse = checkValid(user.getPhone(), Constant.PHONE,user.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
        user.setCreateTime(new Date());     
        user.setPassword(EncryptUtils.MD5Encode(user.getPassword()));
        user.setUpdateTime(new Date());
        user.save();
		return ServerResponse.createBySuccess("注册成功",user);
	}
	
	public ServerResponse<Object> checkValid(String value, String checkFieldName,Integer userId){
		String hql = "";
		if(userId != null){
			hql += " and id <> "+userId;
		}
		if(Constant.USERNAME.equals(checkFieldName)){
			SysUser user = dao.findFirst(" select id from "+TABLENAME+" where username = ? "+hql, value);
			if(user != null){
				return ServerResponse.createByErrorMessage("用户名已存在");
			}
		}else if (Constant.EMAIL.equals(checkFieldName)) {
			SysUser user = dao.findFirst(" select id from "+TABLENAME+" where email = ? "+hql, value);
			if(user != null){
				return ServerResponse.createByErrorMessage("邮箱已存在");
			}
		}else if(Constant.PHONE.equals(checkFieldName)){
			SysUser user = dao.findFirst(" select id from "+TABLENAME+" where phone = ? "+hql,value);
			if(user != null){
				return ServerResponse.createByErrorMessage("手机号已存在");
			}
		}
		return ServerResponse.createBySuccessMessage("校验通过");
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
	public ServerResponse deleteByIds(Integer[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
			if(dao.deleteById(ids)){
				return ServerResponse.createBySuccessMessage("删除成功");
			}else {
				return ServerResponse.createByErrorMessage("删除失败");
			}
		}
		return ServerResponse.createByErrorMessage("参数不能为空");
	}

	@Override
	public ServerResponse<Object> updateUser(SysUser uservo) {
		ServerResponse  validResponse= checkValid(uservo.getUsername(), Constant.USERNAME,uservo.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		validResponse = checkValid(uservo.getEmail(), Constant.EMAIL,uservo.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		validResponse = checkValid(uservo.getPhone(), Constant.PHONE,uservo.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
		SysUser user = findUserById(uservo.getId());
		if(user != null){
			user.setEmail(uservo.getEmail());
			user.setUsername(uservo.getUsername());
			user.setStatus(uservo.getStatus());
			user.setPhone(uservo.getPhone());
			user.setUpdateTime(new Date());
			user.setRoleId(uservo.getRoleId());
			if(user.update()) {
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("用户不存在");
		}
	}
}
