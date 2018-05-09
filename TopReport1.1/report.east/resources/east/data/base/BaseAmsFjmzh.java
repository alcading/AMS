package resources.east.data.base;

import java.math.BigDecimal;
import java.util.Date;
import resources.east.data.pub.AmsFjmzhId;
import resources.east.data.pub.EastGrhqckfhzmxjlTmpId;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzh implements java.io.Serializable {

	public BaseAmsFjmzh(AmsFjmzhId id, String accountnumber, String closedaccount, String duediligenceind,
			String selfcertification, BigDecimal accountbalance, String accountholdertype, String openingfiname,
			BigDecimal payment, String ind_name, String ind_gender, String ind_address, String ind_phoneno,
			String ind_idtype, String ind_idnumber, String ind_rescountrycode, String ind_tin, String ind_explanation,
			String ind_nationality, Date ind_birthinfo, String org_name, String org_address, String org_phoneno,
			String org_rescountrycode, String org_tin, String org_explanation, String con_name,
			String con_ctrlgpersontype, String con_nationality, String con_address, String con_rescountrycode,
			String con_tin, String con_explanation, Date con_birthinfo) {
		this.id = id;
		this.accountnumber = accountnumber;
		this.closedaccount = closedaccount;
		this.duediligenceind = duediligenceind;
		this.selfcertification = selfcertification;
		this.accountbalance = accountbalance;
		this.accountholdertype = accountholdertype;
		this.openingfiname = openingfiname;
		this.payment = payment;
		this.ind_name = ind_name;
		this.ind_gender = ind_gender;
		this.ind_address = ind_address;
		this.ind_phoneno = ind_phoneno;
		this.ind_idtype = ind_idtype;
		this.ind_idnumber = ind_idnumber;
		this.ind_rescountrycode = ind_rescountrycode;
		this.ind_tin = ind_tin;
		this.ind_explanation = ind_explanation;
		this.ind_nationality = ind_nationality;
		this.ind_birthinfo = ind_birthinfo;
		this.org_name = org_name;
		this.org_address = org_address;
		this.org_phoneno = org_phoneno;
		this.org_rescountrycode = org_rescountrycode;
		this.org_tin = org_tin;
		this.org_explanation = org_explanation;
		this.con_name = con_name;
		this.con_ctrlgpersontype = con_ctrlgpersontype;
		this.con_nationality = con_nationality;
		this.con_address = con_address;
		this.con_rescountrycode = con_rescountrycode;
		this.con_tin = con_tin;
		this.con_explanation = con_explanation;
		this.con_birthinfo = con_birthinfo;
	}
	// Fields
    public BaseAmsFjmzh(){
    	
    }
	public BaseAmsFjmzh(AmsFjmzhId id) {
		this.id = id;
	}

	public AmsFjmzhId getId() {
		return id;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public String getClosedaccount() {
		return closedaccount;
	}
	public String getDuediligenceind() {
		return duediligenceind;
	}
	public String getSelfcertification() {
		return selfcertification;
	}
	public BigDecimal getAccountbalance() {
		return accountbalance;
	}
	public String getAccountholdertype() {
		return accountholdertype;
	}
	public String getOpeningfiname() {
		return openingfiname;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public String getInd_name() {
		return ind_name;
	}
	public String getInd_gender() {
		return ind_gender;
	}
	public String getInd_address() {
		return ind_address;
	}
	public String getInd_phoneno() {
		return ind_phoneno;
	}
	public String getInd_idtype() {
		return ind_idtype;
	}
	public String getInd_idnumber() {
		return ind_idnumber;
	}
	public String getInd_rescountrycode() {
		return ind_rescountrycode;
	}
	public String getInd_tin() {
		return ind_tin;
	}
	public String getInd_explanation() {
		return ind_explanation;
	}
	public String getInd_nationality() {
		return ind_nationality;
	}
	public Date getInd_birthinfo() {
		return ind_birthinfo;
	}
	public String getOrg_name() {
		return org_name;
	}
	public String getOrg_address() {
		return org_address;
	}
	public String getOrg_phoneno() {
		return org_phoneno;
	}
	public String getOrg_rescountrycode() {
		return org_rescountrycode;
	}
	public String getOrg_tin() {
		return org_tin;
	}
	public String getOrg_explanation() {
		return org_explanation;
	}
	public String getCon_name() {
		return con_name;
	}
	public String getCon_ctrlgpersontype() {
		return con_ctrlgpersontype;
	}
	public String getCon_nationality() {
		return con_nationality;
	}
	public String getCon_address() {
		return con_address;
	}
	public String getCon_rescountrycode() {
		return con_rescountrycode;
	}
	public String getCon_tin() {
		return con_tin;
	}
	public String getCon_explanation() {
		return con_explanation;
	}
	public Date getCon_birthinfo() {
		return con_birthinfo;
	}
	public void setId(AmsFjmzhId id) {
		this.id = id;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public void setClosedaccount(String closedaccount) {
		this.closedaccount = closedaccount;
	}
	public void setDuediligenceind(String duediligenceind) {
		this.duediligenceind = duediligenceind;
	}
	public void setSelfcertification(String selfcertification) {
		this.selfcertification = selfcertification;
	}
	public void setAccountbalance(BigDecimal accountbalance) {
		this.accountbalance = accountbalance;
	}
	public void setAccountholdertype(String accountholdertype) {
		this.accountholdertype = accountholdertype;
	}
	public void setOpeningfiname(String openingfiname) {
		this.openingfiname = openingfiname;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public void setInd_name(String ind_name) {
		this.ind_name = ind_name;
	}
	public void setInd_gender(String ind_gender) {
		this.ind_gender = ind_gender;
	}
	public void setInd_address(String ind_address) {
		this.ind_address = ind_address;
	}
	public void setInd_phoneno(String ind_phoneno) {
		this.ind_phoneno = ind_phoneno;
	}
	public void setInd_idtype(String ind_idtype) {
		this.ind_idtype = ind_idtype;
	}
	public void setInd_idnumber(String ind_idnumber) {
		this.ind_idnumber = ind_idnumber;
	}
	public void setInd_rescountrycode(String ind_rescountrycode) {
		this.ind_rescountrycode = ind_rescountrycode;
	}
	public void setInd_tin(String ind_tin) {
		this.ind_tin = ind_tin;
	}
	public void setInd_explanation(String ind_explanation) {
		this.ind_explanation = ind_explanation;
	}
	public void setInd_nationality(String ind_nationality) {
		this.ind_nationality = ind_nationality;
	}
	public void setInd_birthinfo(Date ind_birthinfo) {
		this.ind_birthinfo = ind_birthinfo;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public void setOrg_address(String org_address) {
		this.org_address = org_address;
	}
	public void setOrg_phoneno(String org_phoneno) {
		this.org_phoneno = org_phoneno;
	}
	public void setOrg_rescountrycode(String org_rescountrycode) {
		this.org_rescountrycode = org_rescountrycode;
	}
	public void setOrg_tin(String org_tin) {
		this.org_tin = org_tin;
	}
	public void setOrg_explanation(String org_explanation) {
		this.org_explanation = org_explanation;
	}
	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}
	public void setCon_ctrlgpersontype(String con_ctrlgpersontype) {
		this.con_ctrlgpersontype = con_ctrlgpersontype;
	}
	public void setCon_nationality(String con_nationality) {
		this.con_nationality = con_nationality;
	}
	public void setCon_address(String con_address) {
		this.con_address = con_address;
	}
	public void setCon_rescountrycode(String con_rescountrycode) {
		this.con_rescountrycode = con_rescountrycode;
	}
	public void setCon_tin(String con_tin) {
		this.con_tin = con_tin;
	}
	public void setCon_explanation(String con_explanation) {
		this.con_explanation = con_explanation;
	}
	public void setCon_birthinfo(Date con_birthinfo) {
		this.con_birthinfo = con_birthinfo;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	 private AmsFjmzhId id;
	  private String accountnumber;
	  private String closedaccount;
	  private String duediligenceind;
	  private String selfcertification;
	  private BigDecimal accountbalance;
	  private String accountholdertype;
	  private String openingfiname;
	  private BigDecimal payment;
	  private String ind_name;
	  private String ind_gender;
	  private String ind_address;
	  private String ind_phoneno;
	  private String ind_idtype;
	  private String ind_idnumber;
	  private String ind_rescountrycode;
	  private String ind_tin;
	  private String ind_explanation;
	  private String ind_nationality;
	  private Date ind_birthinfo;
	  private String org_name;
	  private String org_address;
	  private String org_phoneno;
	  private String org_rescountrycode;
	  private String org_tin;
	  private String org_explanation;
	  private String con_name;
	  private String con_ctrlgpersontype;
	  private String con_nationality;
	  private String con_address;
	  private String con_rescountrycode;
	  private String con_tin;
	  private String con_explanation;
	  private Date con_birthinfo;

}