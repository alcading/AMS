package resources.east.data.base;


/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhKZR implements java.io.Serializable {
	
	public BaseAmsFjmzhKZR(String kzriAccID, String kzrCtrlgPersonType, String kzrdBirthDate, String kzrBirthCity,
			String kzrBirthCountryCode, String kzrFormerCountryName, String kzrNationality, String kzrResCountryCode,
			String kzrIssuedBy, String kzrsinType, String kzrTIN, String kzrExplanation) {
		super();
		this.kzriAccID = kzriAccID;
		this.kzrCtrlgPersonType = kzrCtrlgPersonType;
		this.kzrdBirthDate = kzrdBirthDate;
		this.kzrBirthCity = kzrBirthCity;
		this.kzrBirthCountryCode = kzrBirthCountryCode;
		this.kzrFormerCountryName = kzrFormerCountryName;
		this.kzrNationality = kzrNationality;
		this.kzrResCountryCode = kzrResCountryCode;
		this.kzrIssuedBy = kzrIssuedBy;
		this.kzrsinType = kzrsinType;
		this.kzrTIN = kzrTIN;
		this.kzrExplanation = kzrExplanation;
	}
	
	/**
	 * 非居民控制人信息
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	  private String kzriAccID;
	  private String kzrCtrlgPersonType;
	  private String kzrdBirthDate;
	  private String kzrBirthCity;
	  private String kzrBirthCountryCode;
	  private String kzrFormerCountryName;
	  private String kzrNationality;
	  private String kzrResCountryCode;
	  private String kzrIssuedBy;
	  private String kzrsinType;
	  private String kzrTIN;
	  private String kzrExplanation;
	  
	  public BaseAmsFjmzhKZR(){}
	  
	public String getKzriAccID() {
		return kzriAccID;
	}
	public void setKzriAccID(String kzriAccID) {
		this.kzriAccID = kzriAccID;
	}
	public String getKzrCtrlgPersonType() {
		return kzrCtrlgPersonType;
	}
	public void setKzrCtrlgPersonType(String kzrCtrlgPersonType) {
		this.kzrCtrlgPersonType = kzrCtrlgPersonType;
	}
	public String getKzrdBirthDate() {
		return kzrdBirthDate;
	}
	public void setKzrdBirthDate(String kzrdBirthDate) {
		this.kzrdBirthDate = kzrdBirthDate;
	}
	public String getKzrBirthCity() {
		return kzrBirthCity;
	}
	public void setKzrBirthCity(String kzrBirthCity) {
		this.kzrBirthCity = kzrBirthCity;
	}
	public String getKzrBirthCountryCode() {
		return kzrBirthCountryCode;
	}
	public void setKzrBirthCountryCode(String kzrBirthCountryCode) {
		this.kzrBirthCountryCode = kzrBirthCountryCode;
	}
	public String getKzrFormerCountryName() {
		return kzrFormerCountryName;
	}
	public void setKzrFormerCountryName(String kzrFormerCountryName) {
		this.kzrFormerCountryName = kzrFormerCountryName;
	}
	public String getKzrNationality() {
		return kzrNationality;
	}
	public void setKzrNationality(String kzrNationality) {
		this.kzrNationality = kzrNationality;
	}
	public String getKzrResCountryCode() {
		return kzrResCountryCode;
	}
	public void setKzrResCountryCode(String kzrResCountryCode) {
		this.kzrResCountryCode = kzrResCountryCode;
	}
	public String getKzrIssuedBy() {
		return kzrIssuedBy;
	}
	public void setKzrIssuedBy(String kzrIssuedBy) {
		this.kzrIssuedBy = kzrIssuedBy;
	}
	public String getKzrsinType() {
		return kzrsinType;
	}
	public void setKzrsinType(String kzrsinType) {
		this.kzrsinType = kzrsinType;
	}
	public String getKzrTIN() {
		return kzrTIN;
	}
	public void setKzrTIN(String kzrTIN) {
		this.kzrTIN = kzrTIN;
	}
	public String getKzrExplanation() {
		return kzrExplanation;
	}
	public void setKzrExplanation(String kzrExplanation) {
		this.kzrExplanation = kzrExplanation;
	}
	  
}