package resources.east.data.base;

import java.sql.Timestamp;
import java.util.Date;

public class BaseAmsDszhFeedback implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timestamp date;
	private String feedback_type;
	private String file_name;
	private Integer success_num;
	private Integer fail_num;
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getFeedback_type() {
		return feedback_type;
	}
	public void setFeedback_type(String feedback_type) {
		this.feedback_type = feedback_type;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Integer getSuccess_num() {
		return success_num;
	}
	public void setSuccess_num(Integer success_num) {
		this.success_num = success_num;
	}
	public Integer getFail_num() {
		return fail_num;
	}
	public void setFail_num(Integer fail_num) {
		this.fail_num = fail_num;
	}
	@Override
	public String toString() {
		return "DszhFeedback [date=" + date + ", feedback_type=" + feedback_type + ", file_name=" + file_name
				+ ", success_num=" + success_num + ", fail_num=" + fail_num + "]";
	}
}
