package resources.east.data.base;

public class BaseAms_jyjgdm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jgdm;
	private String jgnr;
	
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public String getJgnr() {
		return jgnr;
	}
	public void setJgnr(String jgnr) {
		this.jgnr = jgnr;
	}
	@Override
	public String toString() {
		return "BaseAms_jyjgdm [jgdm=" + jgdm + ", jgnr=" + jgnr + "]";
	}
}
