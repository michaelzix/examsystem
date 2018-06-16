package exam.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.handler.Handler;

public class MyHandler extends Handler{
	
	private final static Logger logger = LoggerFactory.getLogger(MyHandler.class);

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		String contextPath = request.getContextPath();
		if(logger.isDebugEnabled()){
			logger.debug("contextPath = {}, target={}",contextPath,target);
		}
		if(StringUtils.isNotBlank(contextPath)){
			if(target.indexOf("/asset") > 0){
				target = contextPath+target;
			}
		}
		next.handle(target, request, response, isHandled);
	}
	

}
