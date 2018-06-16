package exam.Interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import exam.common.Constant;

public class RequestInViewInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		
		Controller targer = inv.getController();
		inv.invoke(); 
		HttpServletRequest request = targer.getRequest();
	     targer.setAttr(Constant.REQUEST_KEY, request);
		
	}

}
