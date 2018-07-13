package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhGR implements java.io.Serializable {

	
	
	public BaseAmsFjmzhGR(String griAccID, String grGender, String grPhoneNo, String grIDType, String grIDNumber,
			String grBirthDate, String grBirthCity, String grBirthCountryCode, String grFormerCountryName,
			String grIssuedBy, String grsinType, String grTIN, String grExplanation, String grNationality) {
		super();
		this.griAccID = griAccID;
		this.grGender = grGender;
		this.grPhoneNo = grPhoneNo;
		this.grIDType = grIDType;
		this.grIDNumber = grIDNumber;
		this.grBirthDate = grBirthDate;
		this.grBirthCity = grBirthCity;
		this.grBirthCountryCode = grBirthCountryCode;
		this.grFormerCountryName = grFormerCountryName;
		this.grIssuedBy = grIssuedBy;
		this.grsinType = grsinType;
		this.grTIN = grTIN;
		this.grExplanation = grExplanation;
		this.grNationality = grNationality;
	}
	/**
	 * 非居民个人信息
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	  private String griAccID;
	  private String grGender;
	  private String grPhoneNo;
	  private String grIDType;
	  private String grIDNumber;
	  private String grBirthDate;
	  private String grBirthCity;
	  private String grBirthCountryCode;
	  private String grFormerCountryName;
	  private String grIssuedBy;
	  private String grsinType;
	  private String grTIN;
	  private String grExplanation;
	  private String grNationality;
	
	  public BaseAmsFjmzhGR(){}
	  
	public String getGriAccID() {
		return griAccID;
	}
	public void setGriAccID(String griAccID) {
		this.griAccID = griAccID;
	}
	public String getGrGender() {
		return grGender;
	}
	public void setGrGender(String grGender) {
		this.grGender = grGender;
	}
	public String getGrPhoneNo() {
		return grPhoneNo;
	}
	public void setGrPhoneNo(String grPhoneNo) {
		this.grPhoneNo = grPhoneNo;
	}
	public String getGrIDType() {
		return grIDType;
	}
	public void setGrIDType(String grIDType) {
		this.grIDType = grIDType;
	}
	public String getGrIDNumber() {
		return grIDNumber;
	}
	public void setGrIDNumber(String grIDNumber) {
		this.grIDNumber = grIDNumber;
	}
	public String getGrBirthDate() {
		return grBirthDate;
	}
	public void setGrBirthDate(String grBirthDate) {
		this.grBirthDate = grBirthDate;
	}
	public String getGrBirthCity() {
		return grBirthCity;
	}
	public void setGrBirthCity(String grBirthCity) {
		this.grBirthCity = grBirthCity;
	}
	public String getGrBirthCountryCode() {
		return grBirthCountryCode;
	}
	public void setGrBirthCountryCode(String grBirthCountryCode) {
		this.grBirthCountryCode = grBirthCountryCode;
	}
	public String getGrFormerCountryName() {
		return grFormerCountryName;
	}
	public void setGrFormerCountryName(String grFormerCountryName) {
		this.grFormerCountryName = grFormerCountryName;
	}
	public String getGrIssuedBy() {
		return grIssuedBy;
	}
	public void setGrIssuedBy(String grIssuedBy) {
		this.grIssuedBy = grIssuedBy;
	}
	public String getGrsinType() {
		return grsinType;
	}
	public void setGrsinType(String grsinType) {
		this.grsinType = grsinType;
	}
	public String getGrTIN() {
		return grTIN;
	}
	public void setGrTIN(String grTIN) {
		this.grTIN = grTIN;
	}
	public String getGrExplanation() {
		return grExplanation;
	}
	public void setGrExplanation(String grExplanation) {
		this.grExplanation = grExplanation;
	}
	public String getGrNationality() {
		return grNationality;
	}
	public void setGrNationality(String grNationality) {
		this.grNationality = grNationality;
	}
	  
	  

}