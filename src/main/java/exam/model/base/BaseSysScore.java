package exam.model.base;


/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysScore<M extends BaseSysScore<M>> extends BaseModel<M> {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setScore(java.math.BigDecimal score) {
		set("score", score);
	}
	
	public java.math.BigDecimal getScore() {
		return get("score");
	}

	public void setCnname(java.lang.String cnname) {
		set("cnname", cnname);
	}
	
	public java.lang.String getCnname() {
		return getStr("cnname");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public void setPaperId(java.lang.Integer paperId) {
		set("paper_id", paperId);
	}
	
	public java.lang.Integer getPaperId() {
		return getInt("paper_id");
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

	public void setKsTime(java.util.Date ksTime) {
		set("ks_time", ksTime);
	}
	
	public java.util.Date getKsTime() {
		return get("ks_time");
	}

	public void setClassStr(java.lang.String Class) {
		set("_class", Class);
	}
	
	public java.lang.String getClassStr() {
		return getStr("_class");
	}

	public void setDepartment(java.lang.String department) {
		set("department", department);
	}
	
	public java.lang.String getDepartment() {
		return getStr("department");
	}

	public void setStudentCode(java.lang.String studentCode) {
		set("student_code", studentCode);
	}
	
	public java.lang.String getStudentCode() {
		return getStr("student_code");
	}

}
