package resources.east.data.base;


/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhXM implements java.io.Serializable {
	
	public BaseAmsFjmzhXM(String xiAccID, String xNameType, String xLastName, String xMiddleName, String xFirstName,
			String xNameCN, String xPrecedingTitle, String xTitle, String xNamePrefix, String xGenerationIdentifier,
			String xSuffix, String xGeneralSuffix, String xStyle) {
		super();
		this.xiAccID = xiAccID;
		this.xNameType = xNameType;
		this.xLastName = xLastName;
		this.xMiddleName = xMiddleName;
		this.xFirstName = xFirstName;
		this.xNameCN = xNameCN;
		this.xPrecedingTitle = xPrecedingTitle;
		this.xTitle = xTitle;
		this.xNamePrefix = xNamePrefix;
		this.xGenerationIdentifier = xGenerationIdentifier;
		this.xSuffix = xSuffix;
		this.xGeneralSuffix = xGeneralSuffix;
		this.xStyle = xStyle;
	}
	/**
	 * 非居民姓名详情
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	  private String xiAccID;
	  private String xNameType;
	  private String xLastName;
	  private String xMiddleName;
	  private String xFirstName;
	  private String xNameCN;
	  private String xPrecedingTitle;
	  private String xTitle;
	  private String xNamePrefix;
	  private String xGenerationIdentifier;
	  private String xSuffix;
	  private String xGeneralSuffix;
	  private String xStyle;
	  
	  public BaseAmsFjmzhXM(){}
	  
	public String getXiAccID() {
		return xiAccID;
	}
	public void setXiAccID(String xiAccID) {
		this.xiAccID = xiAccID;
	}
	public String getxNameType() {
		return xNameType;
	}
	public void setxNameType(String xNameType) {
		this.xNameType = xNameType;
	}
	public String getxLastName() {
		return xLastName;
	}
	public void setxLastName(String xLastName) {
		this.xLastName = xLastName;
	}
	public String getxMiddleName() {
		return xMiddleName;
	}
	public void setxMiddleName(String xMiddleName) {
		this.xMiddleName = xMiddleName;
	}
	public String getxFirstName() {
		return xFirstName;
	}
	public void setxFirstName(String xFirstName) {
		this.xFirstName = xFirstName;
	}
	public String getxNameCN() {
		return xNameCN;
	}
	public void setxNameCN(String xNameCN) {
		this.xNameCN = xNameCN;
	}
	public String getxPrecedingTitle() {
		return xPrecedingTitle;
	}
	public void setxPrecedingTitle(String xPrecedingTitle) {
		this.xPrecedingTitle = xPrecedingTitle;
	}
	public String getxTitle() {
		return xTitle;
	}
	public void setxTitle(String xTitle) {
		this.xTitle = xTitle;
	}
	public String getxNamePrefix() {
		return xNamePrefix;
	}
	public void setxNamePrefix(String xNamePrefix) {
		this.xNamePrefix = xNamePrefix;
	}
	public String getxGenerationIdentifier() {
		return xGenerationIdentifier;
	}
	public void setxGenerationIdentifier(String xGenerationIdentifier) {
		this.xGenerationIdentifier = xGenerationIdentifier;
	}
	public String getxSuffix() {
		return xSuffix;
	}
	public void setxSuffix(String xSuffix) {
		this.xSuffix = xSuffix;
	}
	public String getxGeneralSuffix() {
		return xGeneralSuffix;
	}
	public void setxGeneralSuffix(String xGeneralSuffix) {
		this.xGeneralSuffix = xGeneralSuffix;
	}
	public String getxStyle() {
		return xStyle;
	}
	public void setxStyle(String xStyle) {
		this.xStyle = xStyle;
	}

}