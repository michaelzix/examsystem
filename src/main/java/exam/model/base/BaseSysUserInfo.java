package exam.model.base;


/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysUserInfo<M extends BaseSysUserInfo<M>> extends BaseModel<M>{

	public void setCnname(java.lang.String cnname) {
		set("cnname", cnname);
	}
	
	public java.lang.String getCnname() {
		return getStr("cnname");
	}

	
	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}
	
	public java.lang.String getSex() {
		return getStr("sex");
	}

	public void setAge(java.lang.Integer age) {
		set("age", age);
	}
	
	public java.lang.Integer getAge() {
		return getInt("age");
	}

	public void setClassStr(java.lang.String _class) {
		set("_class", _class);
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

	public void setSysUserId(java.lang.Integer sysUserId) {
		set("sys_user_id", sysUserId);
	}
	
	public java.lang.Integer getSysUserId() {
		return getInt("sys_user_id");
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

	public void setStudentCode(java.lang.String studentCode) {
		set("student_code", studentCode);
	}
	
	public java.lang.String getStudentCode() {
		return getStr("student_code");
	}
	public void setBirthday(java.util.Date Birthday) {
		set("birthday", Birthday);
	}
	
	public java.util.Date getBirthday() {
		return get("birthday");
	}

	
	
	
}
