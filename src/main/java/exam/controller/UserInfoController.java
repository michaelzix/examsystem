package exam.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Page;

import exam.common.Constant;
import exam.model.SysUser;
import exam.model.SysUserInfo;
import exam.service.IUserInfoService;
import exam.service.impl.UserInfoServiceImpl;


public class UserInfoController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IUserInfoService userInfoService = new UserInfoServiceImpl();

   
    public void load(){
        int userId = getParaToInt("userId",0);
        setAttr("userInfo",userInfoService.findUserInfoByUserId(userId));
        setAttr("userId", userId);
        render("userInfo.html");
    }

    
    public void update(){
    	SysUserInfo userInfo = getBean(SysUserInfo.class, "userInfo", true);
    	logger.debug("{uesrInfo=}",userInfo);
    	if(userInfo.isNew()){
    		renderJson(userInfoService.createUserInfo(userInfo));
    	}else{
    		renderJson(userInfoService.updateSysUserInfo(userInfo));
    	}
    }
    
    public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        String name = getPara("name");
        String hql = "";
        if(StringUtils.isNoneBlank(name)){
        	hql += " and cnname like '%"+name+"%' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysUserInfo> page = userInfoService.findUserInfoList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }
    
    
    public void list(){
    	render("selectUserlist.html");
    }
  
}
