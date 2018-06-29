package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseBrnoJbcdLink implements java.io.Serializable {

	
	public BaseBrnoJbcdLink(String brNo, String jbCode) {
		super();
		this.brNo = brNo;
		this.jbCode = jbCode;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	
	
	   private String brNo;
	   private String jbCode;
		// Fields
	    public BaseBrnoJbcdLink(){
	    	
	    }
		public String getBrNo() {
			return brNo;
		}
		public void setBrNo(String brNo) {
			this.brNo = brNo;
		}
		public String getJbCode() {
			return jbCode;
		}
		public void setJbCode(String jbCode) {
			this.jbCode = jbCode;
		}

}