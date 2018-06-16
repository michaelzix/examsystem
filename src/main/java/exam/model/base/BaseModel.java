package exam.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

public class BaseModel<M extends Model> extends Model<M> implements IBean {
	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public boolean isNew() {
		 return getId() == null;
	}

}
