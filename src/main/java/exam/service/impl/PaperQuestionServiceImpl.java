package exam.service.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Page;
import exam.common.ServerResponse;
import exam.model.SysPaperQuestion;
import exam.service.IPaperQuestionService;
public class PaperQuestionServiceImpl implements IPaperQuestionService{
	private static final SysPaperQuestion dao = new SysPaperQuestion().dao();
	
	private static final String TABLENAME="sys_paper_question";
		
	@Override
	public SysPaperQuestion findPaperQuestionById(Integer id){
		SysPaperQuestion  role = dao.findById(id);
		if(role == null){
			role = new SysPaperQuestion();
		}
		return role;
	}

	@Override
	public Page<SysPaperQuestion> findPaperQuestionList(String hql,Integer pageNum,Integer pageSize){
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
		
	
	
	@Override
	public ServerResponse<Object> createPaperQuestion(SysPaperQuestion question) {
		question.setUpdateTime(new Date());
		if(question.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updatePaperQuestion(SysPaperQuestion questionVO) {
		SysPaperQuestion question = findPaperQuestionById(questionVO.getId());
		if(question != null){
			question.setType(questionVO.getType());
			question.setAnswer(questionVO.getAnswer());
			question.setContent(questionVO.getContent());
			question.setKeyWord(questionVO.getKeyWord());
			question.setOption1(questionVO.getOption1());
			question.setOption2(questionVO.getOption2());
			question.setOption3(questionVO.getOption3());
			question.setOption4(questionVO.getOption4());
			question.setOption5(questionVO.getOption5());
			question.setOption6(questionVO.getOption6());
			question.setSource(questionVO.getKeyWord());
			question.setUpdateTime(new Date());
			if(question.update()) {
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("角色不存在");
		}
	}
	
}
