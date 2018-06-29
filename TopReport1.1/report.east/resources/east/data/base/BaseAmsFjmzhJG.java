package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhJG implements java.io.Serializable {
	
	
	
	public BaseAmsFjmzhJG(String jgiAccID, String jgPhoneNo, String jgNameType, String jgOrganisationNameEN,
			String jgOrganisationNameCN, String jgIssuedBy, String jgsinType, String jgTIN, String jgExplanation) {
		super();
		this.jgiAccID = jgiAccID;
		this.jgPhoneNo = jgPhoneNo;
		this.jgNameType = jgNameType;
		this.jgOrganisationNameEN = jgOrganisationNameEN;
		this.jgOrganisationNameCN = jgOrganisationNameCN;
		this.jgIssuedBy = jgIssuedBy;
		this.jgsinType = jgsinType;
		this.jgTIN = jgTIN;
		this.jgExplanation = jgExplanation;
	}
	/**
	 * 非居民账户机构信息
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	  private String jgiAccID;
	  private String jgPhoneNo;
	  private String jgNameType;
	  private String jgOrganisationNameEN;
	  private String jgOrganisationNameCN;
	  private String jgIssuedBy;
	  private String jgsinType;
	  private String jgTIN;
	  private String jgExplanation;
	  
	  public BaseAmsFjmzhJG(){}
	  
	public String getJgiAccID() {
		return jgiAccID;
	}
	public void setJgiAccID(String jgiAccID) {
		this.jgiAccID = jgiAccID;
	}
	public String getJgPhoneNo() {
		return jgPhoneNo;
	}
	public void setJgPhoneNo(String jgPhoneNo) {
		this.jgPhoneNo = jgPhoneNo;
	}
	public String getJgNameType() {
		return jgNameType;
	}
	public void setJgNameType(String jgNameType) {
		this.jgNameType = jgNameType;
	}
	public String getJgOrganisationNameEN() {
		return jgOrganisationNameEN;
	}
	public void setJgOrganisationNameEN(String jgOrganisationNameEN) {
		this.jgOrganisationNameEN = jgOrganisationNameEN;
	}
	public String getJgOrganisationNameCN() {
		return jgOrganisationNameCN;
	}
	public void setJgOrganisationNameCN(String jgOrganisationNameCN) {
		this.jgOrganisationNameCN = jgOrganisationNameCN;
	}
	public String getJgIssuedBy() {
		return jgIssuedBy;
	}
	public void setJgIssuedBy(String jgIssuedBy) {
		this.jgIssuedBy = jgIssuedBy;
	}
	public String getJgsinType() {
		return jgsinType;
	}
	public void setJgsinType(String jgsinType) {
		this.jgsinType = jgsinType;
	}
	public String getJgTIN() {
		return jgTIN;
	}
	public void setJgTIN(String jgTIN) {
		this.jgTIN = jgTIN;
	}
	public String getJgExplanation() {
		return jgExplanation;
	}
	public void setJgExplanation(String jgExplanation) {
		this.jgExplanation = jgExplanation;
	}
	  
	  

}