package exam.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Page;

import exam.common.Constant;
import exam.common.ServerResponse;
import exam.model.SysQuestion;
import exam.service.IQuestionService;
public class QuestionServiceImpl implements IQuestionService {
	private static final SysQuestion dao = new SysQuestion().dao();
	
	private static final String TABLENAME="sys_question";
		
	@Override
	public SysQuestion findQuestionById(Integer id){
		SysQuestion  role = dao.findById(id);
		if(role == null){
			role = new SysQuestion();
		}
		return role;
	}

	@Override
	public Page<SysQuestion> findQuestionList(String hql,Integer pageNum,Integer pageSize){
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
	public ServerResponse<Object> checkValid(String value, String checkFieldName,Integer userId){
		String hql = "";
		if(userId != null){
			hql += " and id <> "+userId;
		}
		if(Constant.ROLENAME.equals(checkFieldName)){
			SysQuestion role = dao.findFirst(" select id from "+TABLENAME+" where name = ? "+hql, value);
			if(role != null){
				return ServerResponse.createByErrorMessage("角色名已存在");
			}
		}
		return ServerResponse.createBySuccessMessage("校验通过");
	}
	
	@Override
	public ServerResponse<Object> createQuestion(SysQuestion question) {
		/*ServerResponse  validResponse= checkValid(question.getAnswer(), Constant.ROLENAME,question.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}
*/		question.setCreateTime(new Date());
		question.setUpdateTime(new Date());
		if(question.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updateQuestion(SysQuestion questionVO) {
		/*ServerResponse  validResponse= checkValid(questionVO.getName(), Constant.ROLENAME,questionVO.getId());
		if(!validResponse.isSuccess()){
			return validResponse;
		}*/
		SysQuestion question = findQuestionById(questionVO.getId());
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

	@Override
	public List<SysQuestion> findQuestionList(String hql, Object... pram) {
		// TODO Auto-generated method stub
		return dao.find(" select * from "+TABLENAME+" where 1 = 1 "+StringUtils.defaultString(hql), pram);
	}
	
}
