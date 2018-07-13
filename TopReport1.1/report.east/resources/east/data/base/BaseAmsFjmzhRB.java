package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhRB implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	
	  public BaseAmsFjmzhRB(String rbId, String fjmzhRbN, String fjmzhRbP) {
		super();
		this.rbId = rbId;
		this.fjmzhRbN = fjmzhRbN;
		this.fjmzhRbP = fjmzhRbP;
	}
	  
    public BaseAmsFjmzhRB(){
    	
    }
	  
	private String rbId;
	  private String fjmzhRbN;
	  private String fjmzhRbP;

	public String getRbId() {
		return rbId;
	}
	public void setRbId(String rbId) {
		this.rbId = rbId;
	}
	public String getFjmzhRbN() {
		return fjmzhRbN;
	}
	public void setFjmzhRbN(String fjmzhRbN) {
		this.fjmzhRbN = fjmzhRbN;
	}
	public String getFjmzhRbP() {
		return fjmzhRbP;
	}
	public void setFjmzhRbP(String fjmzhRbP) {
		this.fjmzhRbP = fjmzhRbP;
	}

}