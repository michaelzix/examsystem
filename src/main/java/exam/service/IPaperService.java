package exam.service;

import com.jfinal.plugin.activerecord.Page;

import exam.common.ServerResponse;
import exam.model.SysPaper;

public interface IPaperService {

	public abstract SysPaper findPaperById(Integer id);

	public abstract Page<SysPaper> findPaperList(String hql, Integer pageNum,
			Integer pageSize);

	public abstract ServerResponse deleteById(Integer id);

	public abstract ServerResponse<Object> createPaper(SysPaper papger);

	public abstract ServerResponse<Object> updatePaper(SysPaper papgerVO);

	public abstract void setTotalScore(SysPaper papger);

}