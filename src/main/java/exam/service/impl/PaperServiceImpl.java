package exam.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Page;
import exam.common.ServerResponse;
import exam.model.SysPaper;
import exam.model.SysPaperQuestion;
import exam.model.SysQuestion;
import exam.service.IPaperService;
import exam.service.IQuestionService;
import exam.utils.NumberUtils;
public class PaperServiceImpl implements IPaperService  {
	private static final SysPaper dao = new SysPaper().dao();
	private IQuestionService iQuestionService = new QuestionServiceImpl();
	
	private static final String TABLENAME="sys_paper";
		
	@Override
	public SysPaper findPaperById(Integer id){
		SysPaper  paper = dao.findById(id);
		if(paper == null){
			paper = new SysPaper();
		}
		return paper;
	}

	@Override
	public Page<SysPaper> findPaperList(String hql,Integer pageNum,Integer pageSize){
		return dao.paginate(pageNum,pageSize,"select * "," from "+TABLENAME+" where 1 = 1 " +StringUtils.defaultString(hql,"")+" order by create_time desc ");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		if(dao.deleteById(id)){
			return ServerResponse.createBySuccessMessage("删除成功");
		}else {
			return ServerResponse.createByErrorMessage("删除失败");
		}
	}
		
	
	
	public void OnlinePaper(SysPaper paper){
		if(paper.getStatus().equals("上线")){
			List<SysQuestion> allList = new ArrayList<>();
			  if(paper.getDxtCount() != null){
				 List<SysQuestion> list =  iQuestionService.findQuestionList(" and type = ? ","单选题");
				 if(CollectionUtils.isNotEmpty(list)){
					 Random random = new Random();
					 for (int i= 0,length = paper.getDxtCount();i < length; i++) {
						 int index = random.nextInt(list.size() - 1);
						 System.out.println(index);
						 SysQuestion question = list.get(index);
						 allList.add(question);
					 } 
				 }
			  }
			  if(paper.getDuoxtCount() != null){
				  List<SysQuestion> list =  iQuestionService.findQuestionList(" and type = ? ","多选题");
				  if(CollectionUtils.isNotEmpty(list)){
					  Random random = new Random();
					  for (int i= 0,length = paper.getDuoxtCount();i < length; i++) {
						  SysQuestion question = list.get(random.nextInt(list.size() -1));
						  allList.add(question);
					  } 
				  }
			  }
			  if(paper.getPdtCount() != null){
				  List<SysQuestion> list =  iQuestionService.findQuestionList(" and type = ? ","判断题");
				  if(CollectionUtils.isNotEmpty(list)){
					  Random random = new Random();
					  for (int i= 0,length = paper.getPdtCount();i < length; i++) {
						  SysQuestion question = list.get(random.nextInt(list.size() - 1));
						  allList.add(question);
					  } 
				  }
			  }
			  
			  for (SysQuestion sysQuestion : allList) {
				  SysPaperQuestion sysPaperQuestion = new SysPaperQuestion();
				  try {
					BeanUtils.copyProperties(sysPaperQuestion, sysQuestion);
				} catch (Exception e) {
					e.printStackTrace();
				}
                  sysPaperQuestion.setId(null);
                  sysPaperQuestion.setPaperId(paper.getId());
                  sysPaperQuestion.setCreateTime(new  Date());
                  sysPaperQuestion.setUpdateTime(new Date());
                  if("单选题".equals(sysQuestion.getType())){
                	  sysPaperQuestion.setScore(paper.getDxtScore());
                  }else if("多选题".equals(sysQuestion.getType())){
                	  sysPaperQuestion.setScore(paper.getDuoxtScore());
                  }if("判断题".equals(sysQuestion.getType())){
                	 sysPaperQuestion.setScore(paper.getPdtScore());
                  }      
				  sysPaperQuestion.save();
			  }
			  
		}
	}
	
	
	@Override
	public ServerResponse<Object> createPaper(SysPaper paper) {
		setTotalScore(paper);
		paper.setCreateTime(new Date());
		paper.setUpdateTime(new Date());
		if(paper.save()){
			this.OnlinePaper(paper);
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updatePaper(SysPaper paperVO) {
		SysPaper paper = findPaperById(paperVO.getId());
		if(paper != null){
			Date createTime = paper.getCreateTime();
			try {
				BeanUtils.copyProperties(paper, paperVO);
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setTotalScore(paper);
			paper.setCreateTime(createTime);
			paper.setUpdateTime(new Date());
			if(paper.update()) {
				this.OnlinePaper(paper);
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("试卷不存在");
		}
	}
	
	@Override
	public void setTotalScore(SysPaper paper) {
		paper.setTotalScore(NumberUtils.defaultValue(paper.getPdtScore()).multiply(NumberUtils.IntegerToBigDecimal(paper.getDuoxtCount()))
				.add(NumberUtils.defaultValue(paper.getDuoxtScore()).multiply(NumberUtils.IntegerToBigDecimal(paper.getDuoxtCount())))
				.add(NumberUtils.defaultValue(paper.getDxtScore()).multiply(NumberUtils.IntegerToBigDecimal(paper.getDxtCount()))));
	}
}
