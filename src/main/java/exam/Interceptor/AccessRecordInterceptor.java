package exam.Interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import exam.model.SysAccessLog;
import exam.service.IAccessLogService;
import exam.service.impl.AccessLogServiceImpl;

public class AccessRecordInterceptor implements Interceptor {

	
	private IAccessLogService accessLogService = new AccessLogServiceImpl();
	
	
	
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
	      HttpServletRequest request = inv.getController().getRequest();	
	      String ip = request.getRemoteHost();
	      String agent =	request.getHeader("User-Agent");
	      SysAccessLog sysAccessLog = new SysAccessLog();
	      sysAccessLog.setAssessTime(new Date());
	      sysAccessLog.setIp(ip);
	      if(StringUtils.indexOfIgnoreCase(agent,"windows") > -1){
	    	  sysAccessLog.setOs("windows");
	      }else if(StringUtils.indexOfIgnoreCase(agent,"android") > -1){
	    	  sysAccessLog.setOs("android");
	      }else if(StringUtils.indexOfIgnoreCase(agent,"ios") > -1){
	    	  sysAccessLog.setOs("ios");
	      }
	      sysAccessLog.setUrl(request.getRequestURI());
	      sysAccessLog.setAgent(agent);
	      accessLogService.createAccessLog(sysAccessLog);
	      inv.invoke();
	      
	}

}
