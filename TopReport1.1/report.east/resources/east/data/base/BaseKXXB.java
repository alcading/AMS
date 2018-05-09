package resources.east.data.base;

import java.math.BigDecimal;

import resources.east.data.pub.AmsDszhId;
import resources.east.data.pub.EastGrhqckfhzmxjlTmpId;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseKXXB implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	private String zh;
	private String kh;
	private String kdqr;
	private String zhjz;
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getKdqr() {
		return kdqr;
	}
	public void setKdqr(String kdqr) {
		this.kdqr = kdqr;
	}
	public String getZhjz() {
		return zhjz;
	}
	public void setZhjz(String zhjz) {
		this.zhjz = zhjz;
	}
	public String getXkrq() {
		return xkrq;
	}
	public void setXkrq(String xkrq) {
		this.xkrq = xkrq;
	}
	public String getKzt() {
		return kzt;
	}
	public void setKzt(String kzt) {
		this.kzt = kzt;
	}
	public BaseKXXB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseKXXB(String zh, String kh, String kdqr, String zhjz, String xkrq, String kzt) {
		super();
		this.zh = zh;
		this.kh = kh;
		this.kdqr = kdqr;
		this.zhjz = zhjz;
		this.xkrq = xkrq;
		this.kzt = kzt;
	}
	private String xkrq;
	private String kzt;
}