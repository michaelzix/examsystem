package exam.Interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import exam.common.Constant;
import exam.model.SysUser;
public class AuthenticationInterceptor implements Interceptor {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		//String actionKey = inv.getActionKey();	
		SysUser user =  inv.getController().getSessionAttr(Constant.CUR_USER);
		logger.debug("user={}",user);
		if(user == null){
			inv.getController().redirect("/login");
		}else{
			inv.invoke();
		}
		
	}
	
}
