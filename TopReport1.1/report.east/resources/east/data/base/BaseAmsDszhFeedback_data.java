package resources.east.data.base;

public class BaseAmsDszhFeedback_data implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String filename;
	private String zh;
	private String jgdm;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	
	@Override
	public String toString() {
		return "BaseAmsDszhFeedback_data [filename=" + filename + ", zh=" + zh + ", jgdm=" + jgdm + "]";
	}
}
