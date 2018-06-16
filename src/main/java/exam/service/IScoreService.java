package exam.service;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysScore;

public interface IScoreService {

	public abstract SysScore findScoreById(Integer id);

	public abstract Page<SysScore> findScoreList(String hql, Integer pageNum,
			Integer pageSize);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> createScore(SysScore question);

	public abstract ServerResponse<Object> updateScore(SysScore questionVO);

}