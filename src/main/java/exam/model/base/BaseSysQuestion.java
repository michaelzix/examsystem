package exam.model.base;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysQuestion<M extends BaseSysQuestion<M>> extends BaseModel<M> {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setCreateName(java.lang.String createName) {
		set("create_name", createName);
	}
	
	public java.lang.String getCreateName() {
		return getStr("create_name");
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

	public void setType(java.lang.String type) {
		set("type", type);
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setAnswer(java.lang.String answer) {
		set("answer", answer);
	}
	
	public java.lang.String getAnswer() {
		return getStr("answer");
	}

	public void setSource(java.lang.String source) {
		set("source", source);
	}
	
	public java.lang.String getSource() {
		return getStr("source");
	}

	public void setKeyWord(java.lang.String keyWord) {
		set("key_word", keyWord);
	}
	
	public java.lang.String getKeyWord() {
		return getStr("key_word");
	}

	public void setOption1(java.lang.String option1) {
		set("option1", option1);
	}
	
	public java.lang.String getOption1() {
		return getStr("option1");
	}

	public void setOption2(java.lang.String option2) {
		set("option2", option2);
	}
	
	public java.lang.String getOption2() {
		return getStr("option2");
	}

	public void setOption3(java.lang.String option3) {
		set("option3", option3);
	}
	
	public java.lang.String getOption3() {
		return getStr("option3");
	}

	public void setOption4(java.lang.String option4) {
		set("option4", option4);
	}
	
	public java.lang.String getOption4() {
		return getStr("option4");
	}

	public void setOption5(java.lang.String option5) {
		set("option5", option5);
	}
	
	public java.lang.String getOption5() {
		return getStr("option5");
	}

	public void setOption6(java.lang.String option6) {
		set("option6", option6);
	}
	
	public java.lang.String getOption6() {
		return getStr("option6");
	}

}
