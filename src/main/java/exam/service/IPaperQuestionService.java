package exam.service;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysPaperQuestion;

public interface IPaperQuestionService {

	public abstract SysPaperQuestion findPaperQuestionById(Integer id);

	public abstract Page<SysPaperQuestion> findPaperQuestionList(String hql,
			Integer pageNum, Integer pageSize);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> createPaperQuestion(
			SysPaperQuestion question);

	public abstract ServerResponse<Object> updatePaperQuestion(
			SysPaperQuestion questionVO);

}