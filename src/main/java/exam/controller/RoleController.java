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
import exam.model.SysRole;
import exam.service.IRoleService;
import exam.service.impl.RoleServiceImpl;


public class RoleController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IRoleService roleService = new RoleServiceImpl();

    public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        Date   start =	getParaToDate("start");
        Date   end =	getParaToDate("end");
        String name = getPara("name");
        String hql = "";
        if(start != null){
        	hql += " and DATE_FORMAT(create_time,'%Y-%m-%d') >= '"+DateFormatUtils.format(start, "yyyy-MM-dd")+"' ";
        }
        if(end != null){
        	hql += " and DATE_FORMAT(create_time,'%Y-%m-%d') <= '"+DateFormatUtils.format(end, "yyyy-MM-dd")+"' ";
        }
        if(StringUtils.isNoneBlank(name)){
        	hql += " and name like '%"+name+"%' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysRole> page = roleService.findUserList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }

    public void save() {
        SysRole role = getBean(SysRole.class, "role", true);
        if (role.isNew()) {
            renderJson(roleService.createRole(role));
        } else {
            renderJson(roleService.updateRole(role));
        }
    }

    public void load(){
        int id = getParaToInt("id",0);
        setAttr("role",roleService.findUserById(id));
        render("add.html");
    }

    public void delete(){
        Integer ids = getParaToInt("ids");
        renderJson(roleService.deleteById(ids));
    }

}
