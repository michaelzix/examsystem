package exam.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Page;

import exam.common.Constant;
import exam.model.SysPaper;
import exam.service.IPaperService;
import exam.service.impl.PaperServiceImpl;

public class TestController extends  BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	private IPaperService papgerService = new PaperServiceImpl();
	
	public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        String name = getPara("name");
        String type = getPara("type");
        String hql = " and status = '上线' ";

        if(StringUtils.isNoneBlank(name)){
        	hql += " and name like '%"+name+"%' ";
        }
        if(StringUtils.isNoneBlank(type)){
        	hql += " and type ='"+type+"' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysPaper> page = papgerService.findPaperList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }
}
