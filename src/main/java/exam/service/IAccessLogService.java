package exam.service;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysAccessLog;

public interface IAccessLogService {

	Page<SysAccessLog> findUserList(String hql, Integer pageNum, Integer pageSize);

	ServerResponse deleteById(Integer id);

	ServerResponse<Object> createAccessLog(SysAccessLog AccessLog);

}