package exam.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Page;
import exam.common.ServerResponse;
import exam.model.SysScore;
import exam.service.IScoreService;
public class ScoreServiceImpl implements IScoreService  {
	private static final SysScore dao = new SysScore().dao();
	
	private static final String TABLENAME="sys_score";
		
	@Override
	public SysScore findScoreById(Integer id){
		SysScore  score = dao.findById(id);
		if(score == null){
			score = new SysScore();
		}
		return score;
	}

	@Override
	public Page<SysScore> findScoreList(String hql,Integer pageNum,Integer pageSize){
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
	public ServerResponse<Object> createScore(SysScore question) {
		question.setCreateTime(new Date());
		question.setUpdateTime(new Date());
		if(question.save()){
			return ServerResponse.createBySuccessMessage("创建成功");
		}else{
			return ServerResponse.createByErrorMessage("创建失败");
		}
	}
	
	@Override
	public ServerResponse<Object> updateScore(SysScore questionVO) {
		
		SysScore question = findScoreById(questionVO.getId());
		if(question != null){
			Date createTime = question.getCreateTime();
			try {
				BeanUtils.copyProperties(question, questionVO);
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			question.setCreateTime(createTime);
			question.setUpdateTime(new Date());
			if(question.update()) {
				return ServerResponse.createBySuccessMessage("更新成功");
			}else{
				return ServerResponse.createByErrorMessage("更新失败");
			}
		}else{
			return ServerResponse.createByErrorMessage("不存在");
		}
	}
	
}
