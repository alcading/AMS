package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhDZ implements java.io.Serializable {

	

	public BaseAmsFjmzhDZ(String addressID, String diAccID, String slegalAddressType, String sCountryCode,
			String sAddressEN_AddressFreeEN, String sAddressFixEN_CityEN, String sAddressFixEN_Street,
			String sAddressFixEN_BuildingIden, String sAddressFixEN_SuiteIdentifier,
			String sAddressFixEN_FloorIdentifier, String sAddressFixEN_DistrictName, String sAddressFixEN_POB,
			String sAddressFixEN_PostCode, String sAddressFixEN_CountrySubentity, String sAddressCN_sAddressFreeCN,
			String sAddressFixCN_Province, String sAddressFixCN_CityCN, String sAddressFixCN_DistrictName,
			String sAddressFixCN_PostCode, String dStyle) {
		super();
		this.addressID = addressID;
		this.diAccID = diAccID;
		this.slegalAddressType = slegalAddressType;
		this.sCountryCode = sCountryCode;
		this.sAddressEN_AddressFreeEN = sAddressEN_AddressFreeEN;
		this.sAddressFixEN_CityEN = sAddressFixEN_CityEN;
		this.sAddressFixEN_Street = sAddressFixEN_Street;
		this.sAddressFixEN_BuildingIden = sAddressFixEN_BuildingIden;
		this.sAddressFixEN_SuiteIdentifier = sAddressFixEN_SuiteIdentifier;
		this.sAddressFixEN_FloorIdentifier = sAddressFixEN_FloorIdentifier;
		this.sAddressFixEN_DistrictName = sAddressFixEN_DistrictName;
		this.sAddressFixEN_POB = sAddressFixEN_POB;
		this.sAddressFixEN_PostCode = sAddressFixEN_PostCode;
		this.sAddressFixEN_CountrySubentity = sAddressFixEN_CountrySubentity;
		this.sAddressCN_sAddressFreeCN = sAddressCN_sAddressFreeCN;
		this.sAddressFixCN_Province = sAddressFixCN_Province;
		this.sAddressFixCN_CityCN = sAddressFixCN_CityCN;
		this.sAddressFixCN_DistrictName = sAddressFixCN_DistrictName;
		this.sAddressFixCN_PostCode = sAddressFixCN_PostCode;
		this.dStyle = dStyle;
	}

	/**
	 * 非居民地址信息
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	  private String addressID;
	  private String diAccID;
	  private String slegalAddressType;
	  private String sCountryCode;
	  private String sAddressEN_AddressFreeEN;
	  private String sAddressFixEN_CityEN;
	  private String sAddressFixEN_Street;
	  private String sAddressFixEN_BuildingIden;
	  private String sAddressFixEN_SuiteIdentifier;
	  private String sAddressFixEN_FloorIdentifier;
	  private String sAddressFixEN_DistrictName;
	  private String sAddressFixEN_POB;
	  private String sAddressFixEN_PostCode;
	  private String sAddressFixEN_CountrySubentity;
	  private String sAddressCN_sAddressFreeCN;
	  private String sAddressFixCN_Province;
	  private String sAddressFixCN_CityCN;
	  private String sAddressFixCN_DistrictName;
	  private String sAddressFixCN_PostCode;
	  private String dStyle;
	  
	  public BaseAmsFjmzhDZ(){}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getDiAccID() {
		return diAccID;
	}

	public void setDiAccID(String diAccID) {
		this.diAccID = diAccID;
	}

	public String getSlegalAddressType() {
		return slegalAddressType;
	}

	public void setSlegalAddressType(String slegalAddressType) {
		this.slegalAddressType = slegalAddressType;
	}

	public String getsCountryCode() {
		return sCountryCode;
	}

	public void setsCountryCode(String sCountryCode) {
		this.sCountryCode = sCountryCode;
	}

	public String getsAddressEN_AddressFreeEN() {
		return sAddressEN_AddressFreeEN;
	}

	public void setsAddressEN_AddressFreeEN(String sAddressEN_AddressFreeEN) {
		this.sAddressEN_AddressFreeEN = sAddressEN_AddressFreeEN;
	}

	public String getsAddressFixEN_CityEN() {
		return sAddressFixEN_CityEN;
	}

	public void setsAddressFixEN_CityEN(String sAddressFixEN_CityEN) {
		this.sAddressFixEN_CityEN = sAddressFixEN_CityEN;
	}

	public String getsAddressFixEN_Street() {
		return sAddressFixEN_Street;
	}

	public void setsAddressFixEN_Street(String sAddressFixEN_Street) {
		this.sAddressFixEN_Street = sAddressFixEN_Street;
	}

	public String getsAddressFixEN_BuildingIden() {
		return sAddressFixEN_BuildingIden;
	}

	public void setsAddressFixEN_BuildingIden(String sAddressFixEN_BuildingIden) {
		this.sAddressFixEN_BuildingIden = sAddressFixEN_BuildingIden;
	}

	public String getsAddressFixEN_SuiteIdentifier() {
		return sAddressFixEN_SuiteIdentifier;
	}

	public void setsAddressFixEN_SuiteIdentifier(String sAddressFixEN_SuiteIdentifier) {
		this.sAddressFixEN_SuiteIdentifier = sAddressFixEN_SuiteIdentifier;
	}

	public String getsAddressFixEN_FloorIdentifier() {
		return sAddressFixEN_FloorIdentifier;
	}

	public void setsAddressFixEN_FloorIdentifier(String sAddressFixEN_FloorIdentifier) {
		this.sAddressFixEN_FloorIdentifier = sAddressFixEN_FloorIdentifier;
	}

	public String getsAddressFixEN_DistrictName() {
		return sAddressFixEN_DistrictName;
	}

	public void setsAddressFixEN_DistrictName(String sAddressFixEN_DistrictName) {
		this.sAddressFixEN_DistrictName = sAddressFixEN_DistrictName;
	}

	public String getsAddressFixEN_POB() {
		return sAddressFixEN_POB;
	}

	public void setsAddressFixEN_POB(String sAddressFixEN_POB) {
		this.sAddressFixEN_POB = sAddressFixEN_POB;
	}

	public String getsAddressFixEN_PostCode() {
		return sAddressFixEN_PostCode;
	}

	public void setsAddressFixEN_PostCode(String sAddressFixEN_PostCode) {
		this.sAddressFixEN_PostCode = sAddressFixEN_PostCode;
	}

	public String getsAddressFixEN_CountrySubentity() {
		return sAddressFixEN_CountrySubentity;
	}

	public void setsAddressFixEN_CountrySubentity(String sAddressFixEN_CountrySubentity) {
		this.sAddressFixEN_CountrySubentity = sAddressFixEN_CountrySubentity;
	}

	public String getsAddressCN_sAddressFreeCN() {
		return sAddressCN_sAddressFreeCN;
	}

	public void setsAddressCN_sAddressFreeCN(String sAddressCN_sAddressFreeCN) {
		this.sAddressCN_sAddressFreeCN = sAddressCN_sAddressFreeCN;
	}

	public String getsAddressFixCN_Province() {
		return sAddressFixCN_Province;
	}

	public void setsAddressFixCN_Province(String sAddressFixCN_Province) {
		this.sAddressFixCN_Province = sAddressFixCN_Province;
	}

	public String getsAddressFixCN_CityCN() {
		return sAddressFixCN_CityCN;
	}

	public void setsAddressFixCN_CityCN(String sAddressFixCN_CityCN) {
		this.sAddressFixCN_CityCN = sAddressFixCN_CityCN;
	}

	public String getsAddressFixCN_DistrictName() {
		return sAddressFixCN_DistrictName;
	}

	public void setsAddressFixCN_DistrictName(String sAddressFixCN_DistrictName) {
		this.sAddressFixCN_DistrictName = sAddressFixCN_DistrictName;
	}

	public String getsAddressFixCN_PostCode() {
		return sAddressFixCN_PostCode;
	}

	public void setsAddressFixCN_PostCode(String sAddressFixCN_PostCode) {
		this.sAddressFixCN_PostCode = sAddressFixCN_PostCode;
	}

	public String getdStyle() {
		return dStyle;
	}

	public void setdStyle(String dStyle) {
		this.dStyle = dStyle;
	}
	  
	  
	  

}