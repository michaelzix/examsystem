package exam.model.base;

import org.apache.commons.lang3.StringUtils;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysRole<M extends BaseSysRole<M>> extends BaseModel<M> {

	
	
	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setStatus(java.lang.String status) {
		set("status", status);
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}
	
	public boolean isAdmin() {
		return StringUtils.equals(getName(), "管理员");
	}
	public boolean isTeacher() {
		return StringUtils.equals(getName(), "老师");
	}
	
}