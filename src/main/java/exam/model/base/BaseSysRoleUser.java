package exam.model.base;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysRoleUser<M extends BaseSysRoleUser<M>> extends BaseModel<M> {

	
	public void setRoleId(java.lang.Integer roleId) {
		set("role_id", roleId);
	}
	
	public java.lang.Integer getRoleId() {
		return getInt("role_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

}