package exam.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysQuestion;

public interface IQuestionService {

	public abstract SysQuestion findQuestionById(Integer id);

	public abstract Page<SysQuestion> findQuestionList(String hql,
			Integer pageNum, Integer pageSize);
	
	public abstract List<SysQuestion> findQuestionList(String hql,Object ...pram);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> checkValid(String value,
			String checkFieldName, Integer userId);

	public abstract ServerResponse<Object> createQuestion(SysQuestion question);

	public abstract ServerResponse<Object> updateQuestion(SysQuestion questionVO);

}