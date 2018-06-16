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
import exam.model.SysQuestion;
import exam.service.IQuestionService;
import exam.service.impl.QuestionServiceImpl;


public class QuestionController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IQuestionService questionService = new QuestionServiceImpl();

    public void listJSON(){
        Integer pageNum = getParaToInt("pageNum",Constant.PAGENUM);
        Integer pageSize = getParaToInt("pageSize",Constant.PAGESIZE);
        String name = getPara("name");
        String type = getPara("type");
        String hql = "";

        if(StringUtils.isNoneBlank(name)){
        	hql += " and content like '%"+name+"%' ";
        }
        if(StringUtils.isNoneBlank(type)){
        	hql += " and type ='"+type+"' ";
        }
        if(logger.isDebugEnabled()){
        	logger.debug("hql={}", hql);
        }
        Page<SysQuestion> page = questionService.findQuestionList(hql,pageNum,pageSize);
        //setAttr("page", page);
        Map<String,Object> map = Maps.newHashMap();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data",page.getList());
        map.put("count",page.getTotalRow());
        renderJson(map);
    }

    public void save() {
        SysQuestion question = getBean(SysQuestion.class, "question", true);
        question.setCreateName(getUser().getUserInfo().getCnname());
        if (question.isNew()) {
            renderJson(questionService.createQuestion(question));
        } else {
            renderJson(questionService.updateQuestion(question));
        }
    }

    public void load(){
        int id = getParaToInt("id",0);
        setAttr("question",questionService.findQuestionById(id));
        render("add.html");
    }

    public void delete(){
        Integer ids = getParaToInt("ids");
        renderJson(questionService.deleteById(ids));
    }

}
