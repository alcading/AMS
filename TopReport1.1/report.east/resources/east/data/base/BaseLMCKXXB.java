package resources.east.data.base;

import java.math.BigDecimal;

import resources.east.data.pub.AmsDszhId;
import resources.east.data.pub.EastGrhqckfhzmxjlTmpId;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseLMCKXXB implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	private String ckrxm;
	private String ckrsfzjzl;
	private String ckrsfzjhm;
	private String sfzjdqr;
	private String dqdm;
	private String ckrlb;
	private String ckrgjdq;
	public BaseLMCKXXB(String ckrxm, String ckrsfzjzl, String ckrsfzjhm, String sfzjdqr, String dqdm, String ckrlb,
			String ckrgjdq, String ckrxb, String zh) {
		super();
		this.ckrxm = ckrxm;
		this.ckrsfzjzl = ckrsfzjzl;
		this.ckrsfzjhm = ckrsfzjhm;
		this.sfzjdqr = sfzjdqr;
		this.dqdm = dqdm;
		this.ckrlb = ckrlb;
		this.ckrgjdq = ckrgjdq;
		this.ckrxb = ckrxb;
		this.zh = zh;
	}
	public String getCkrxm() {
		return ckrxm;
	}

	public void setCkrxm(String ckrxm) {
		this.ckrxm = ckrxm;
	}

	public String getCkrsfzjzl() {
		return ckrsfzjzl;
	}

	public void setCkrsfzjzl(String ckrsfzjzl) {
		this.ckrsfzjzl = ckrsfzjzl;
	}

	public String getCkrsfzjhm() {
		return ckrsfzjhm;
	}

	public void setCkrsfzjhm(String ckrsfzjhm) {
		this.ckrsfzjhm = ckrsfzjhm;
	}

	public String getSfzjdqr() {
		return sfzjdqr;
	}

	public void setSfzjdqr(String sfzjdqr) {
		this.sfzjdqr = sfzjdqr;
	}

	public String getDqdm() {
		return dqdm;
	}

	public void setDqdm(String dqdm) {
		this.dqdm = dqdm;
	}

	public String getCkrlb() {
		return ckrlb;
	}

	public void setCkrlb(String ckrlb) {
		this.ckrlb = ckrlb;
	}

	public String getCkrgjdq() {
		return ckrgjdq;
	}

	public void setCkrgjdq(String ckrgjdq) {
		this.ckrgjdq = ckrgjdq;
	}

	public String getCkrxb() {
		return ckrxb;
	}

	public void setCkrxb(String ckrxb) {
		this.ckrxb = ckrxb;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}
	private String ckrxb;
	private String zh;
	// Constructors

	/** default constructor */
	public BaseLMCKXXB() {
	}
}