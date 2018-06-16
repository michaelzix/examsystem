package exam.service.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Page;
import exam.common.ServerResponse;
import exam.model.SysAccessLog;
import exam.service.IAccessLogService;
public class AccessLogServiceImpl implements IAccessLogService {
	private static final SysAccessLog dao = new SysAccessLog().dao();
	
	private static final String TABLENAME="sys_access_log";
		
	
	

	@Override
	public Page<SysAccessLog> findUserList(String hql,Integer pageNum,Integer pageSize){
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
	public ServerResponse<Object> createAccessLog(SysAccessLog AccessLog) {
		AccessLog.setCreateTime(new Date());
		if(AccessLog.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	

	
}
