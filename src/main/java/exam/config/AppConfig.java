package exam.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.handler.Handler;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import exam.Interceptor.AccessRecordInterceptor;
import exam.Interceptor.AuthenticationInterceptor;
import exam.Interceptor.RequestInViewInterceptor;
import exam.common.Constant;
import exam.controller.AccessController;
import exam.controller.HomeController;
import exam.controller.PaperController;
import exam.controller.QuestionController;
import exam.controller.RoleController;
import exam.controller.ScoreController;
import exam.controller.TestController;
import exam.controller.UserController;
import exam.controller.UserInfoController;
import exam.handler.MyHandler;
import exam.model._MappingKit;

public class AppConfig extends JFinalConfig {

    private Prop app = PropKit.use("app.properties");

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setEncoding("UTF-8");

    }

    @Override
    public void configRoute(Routes routes) {
    	routes.addInterceptor(new AccessRecordInterceptor());
    	routes.addInterceptor(new AuthenticationInterceptor());
    	routes.addInterceptor(new RequestInViewInterceptor());
        routes.setBaseViewPath("/WEB-INF/views/");
        routes.add("/",HomeController.class);
        routes.add("/user",UserController.class,"user/");
        routes.add("/userInfo",UserInfoController.class,"user/");
        routes.add("/role",RoleController.class,"role/");
        routes.add("/question",QuestionController.class,"question/");
        routes.add("/paper",PaperController.class,"paper/");
        routes.add("/score",ScoreController.class,"score/");
        routes.add("/test",TestController.class,"test/");
        routes.add("/access",AccessController.class,"access/");
    }

    @Override
    public void configEngine(Engine engine) {
    	engine.addSharedObject("pagesize",Constant.PAGESIZE);
    	engine.addSharedObject("pagenum",Constant.PAGENUM);
    	engine.addSharedObject("pagesizeRank",Constant.PAGESIZE_RANK);
    }

    @Override
    public void configPlugin(Plugins plugins) {

        DruidPlugin dp = new DruidPlugin(app.get("jdbc.url"),app.get("jdbc.username"),app.get("jdbc.password"),app.get("jdbc.driverClass"));
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setShowSql(true);
        plugins.add(arp);
        _MappingKit.mapping(arp);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
    	handlers.add(new MyHandler());

    }
}
