package exam.controller;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Page;
import exam.common.Constant;
import exam.model.SysScore;
import exam.service.IScoreService;
import exam.service.impl.ScoreServiceImpl;


public class ScoreController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IScoreService scoreService = new ScoreServiceImpl();

    public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        String name = getPara("name");
        String type = getPara("type");
        String hql = "";

        if(StringUtils.isNoneBlank(name)){
        	hql += " and name like '%"+name+"%' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysScore> page = scoreService.findScoreList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }

    public void save() {
        SysScore score = getBean(SysScore.class, "score", true);
        if (score.isNew()) {
            renderJson(scoreService.createScore(score));
        } else {
            renderJson(scoreService.updateScore(score));
        }
    }

    public void load(){
        int id = getParaToInt("id",0);
        setAttr("score",scoreService.findScoreById(id));
        render("add.html");
    }

    public void delete(){
        Integer ids = getParaToInt("ids");
        renderJson(scoreService.deleteById(ids));
    }

}
