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
import exam.model.SysAccessLog;
import exam.service.IAccessLogService;
import exam.service.impl.AccessLogServiceImpl;


public class AccessController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IAccessLogService AccessLogService = new AccessLogServiceImpl();

    public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        Date   start =	getParaToDate("start");
        Date   end =	getParaToDate("end");
        String name = getPara("name");
        String hql = "";
        if(start != null){
        	hql += " and DATE_FORMAT(assess_time,'%Y-%m-%d') >= '"+DateFormatUtils.format(start, "yyyy-MM-dd")+"' ";
        }
        if(end != null){
        	hql += " and DATE_FORMAT(assess_time,'%Y-%m-%d') <= '"+DateFormatUtils.format(end, "yyyy-MM-dd")+"' ";
        }
        if(StringUtils.isNoneBlank(name)){
        	hql += " and name like '%"+name+"%' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysAccessLog> page = AccessLogService.findUserList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }

    public void save() {
       
    }


    public void delete(){
        Integer ids = getParaToInt("ids");
        renderJson(AccessLogService.deleteById(ids));
    }

}
