package resources.east.data.base;

import java.math.BigDecimal;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzh implements java.io.Serializable {

	public BaseAmsFjmzh(String accountNumber, String sClosedAccount, String sDueDiligenceInd,
			String sSelfSertification, BigDecimal iAccountBalance, String sACC_currCode, String sAccountHolderType,
			String sOpeningFIName, String sAccountType, String sPaymentType, BigDecimal iPaymentAmnt,
			String sPaymentAmntCurr, String sResCountryCode, String sIssuedBy, String sinType, String TIN,
			String report_status) {
		
		this.accountNumber = accountNumber;
		this.sClosedAccount = sClosedAccount;
		this.sDueDiligenceInd = sDueDiligenceInd;
		this.sSelfSertification = sSelfSertification;
		this.iAccountBalance = iAccountBalance;
		this.sACC_currCode = sACC_currCode;
		this.sAccountHolderType = sAccountHolderType;
		this.sOpeningFIName = sOpeningFIName;
		this.sAccountType = sAccountType;
		this.sPaymentType = sPaymentType;
		this.iPaymentAmnt = iPaymentAmnt;
		this.sPaymentAmntCurr = sPaymentAmntCurr;
		this.sResCountryCode = sResCountryCode;
		this.sIssuedBy = sIssuedBy;
		this.sinType = sinType;
		this.TIN = TIN;
		this.report_status = report_status;
	}
	
	// Fields
    public BaseAmsFjmzh(){
    	
    }

	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getsClosedAccount() {
		return sClosedAccount;
	}

	public void setsClosedAccount(String sClosedAccount) {
		this.sClosedAccount = sClosedAccount;
	}

	public String getsDueDiligenceInd() {
		return sDueDiligenceInd;
	}

	public void setsDueDiligenceInd(String sDueDiligenceInd) {
		this.sDueDiligenceInd = sDueDiligenceInd;
	}

	public String getsSelfSertification() {
		return sSelfSertification;
	}

	public void setsSelfSertification(String sSelfSertification) {
		this.sSelfSertification = sSelfSertification;
	}

	public BigDecimal getiAccountBalance() {
		return iAccountBalance;
	}

	public void setiAccountBalance(BigDecimal iAccountBalance) {
		this.iAccountBalance = iAccountBalance;
	}

	public String getsACC_currCode() {
		return sACC_currCode;
	}

	public void setsACC_currCode(String sACC_currCode) {
		this.sACC_currCode = sACC_currCode;
	}

	public String getsAccountHolderType() {
		return sAccountHolderType;
	}

	public void setsAccountHolderType(String sAccountHolderType) {
		this.sAccountHolderType = sAccountHolderType;
	}

	public String getsOpeningFIName() {
		return sOpeningFIName;
	}

	public void setsOpeningFIName(String sOpeningFIName) {
		this.sOpeningFIName = sOpeningFIName;
	}

	public String getsAccountType() {
		return sAccountType;
	}

	public void setsAccountType(String sAccountType) {
		this.sAccountType = sAccountType;
	}

	public String getsPaymentType() {
		return sPaymentType;
	}

	public void setsPaymentType(String sPaymentType) {
		this.sPaymentType = sPaymentType;
	}

	public BigDecimal getiPaymentAmnt() {
		return iPaymentAmnt;
	}

	public void setiPaymentAmnt(BigDecimal iPaymentAmnt) {
		this.iPaymentAmnt = iPaymentAmnt;
	}

	public String getsPaymentAmntCurr() {
		return sPaymentAmntCurr;
	}

	public void setsPaymentAmntCurr(String sPaymentAmntCurr) {
		this.sPaymentAmntCurr = sPaymentAmntCurr;
	}

	public String getsResCountryCode() {
		return sResCountryCode;
	}

	public void setsResCountryCode(String sResCountryCode) {
		this.sResCountryCode = sResCountryCode;
	}

	public String getsIssuedBy() {
		return sIssuedBy;
	}

	public void setsIssuedBy(String sIssuedBy) {
		this.sIssuedBy = sIssuedBy;
	}

	public String getSinType() {
		return sinType;
	}

	public void setSinType(String sinType) {
		this.sinType = sinType;
	}

	public String getTIN() {
		return TIN;
	}

	public void setTIN(String tIN) {
		TIN = tIN;
	}

	public String getReport_status() {
		return report_status;
	}

	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}

	
	

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	
	private boolean select;
	
	  private String accountNumber;
	  private String sClosedAccount;
	  private String sDueDiligenceInd;
	  private String sSelfSertification;
	  private BigDecimal iAccountBalance;
	  private String sACC_currCode;
	  private String sAccountHolderType;
	  private String sOpeningFIName;
	  private String sAccountType;
	  private String sPaymentType;
	  private BigDecimal iPaymentAmnt;
	  private String sPaymentAmntCurr;
	  private String sResCountryCode;
	  private String sIssuedBy;
	  private String sinType;
	  private String TIN;
	  private String report_status;

}