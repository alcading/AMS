package resources.east.data.base;

import java.math.BigDecimal;

import resources.east.data.pub.AmsDszhId;
import resources.east.data.pub.EastGrhqckfhzmxjlTmpId;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsDszh implements java.io.Serializable {

	// Fields

	/**

	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;

	private boolean select;
	
//	private String lbhdate;
	
	private String ckrxm;
	private String ckrsfzjzl;
	private String ckrsfzjhm;
	private String sfzjdqr;
	private String dqdm;
	private String ckrlb;
	private String ckrgjdq;
	

	private String ckrxb;
	private String ckryb;
	private String ckrdz;
	private String ckrdh;
	private String dlrmc;
	private String dlrsfzjzl;
	private String dlrsfzjhm;
	private String dlrgjdq;
	private String dlrdh;
	private String jrjgbm;
	private String zh;
	private String zhzl;
	//private String kh;
	//private String kdqr;
	//private String zhjz;
	//private String xkrq;
	//private String kzt;
	private String zhlx;
	private String ylzhzh;
	private String ylzhjgbm;
	private String khrq;
	private String xhrq;
	private String zhzt;
	private String bz;
	private String sfjrbzk;
	private String sfshbzk;
	private String hsjg;
	private String wfhsyy;
	private String czff;
	private String xxlx;
	private String khqd;
	private String remarks;
	private String jlzt;
	private String reserve4;
	private String reserve5;
	private String jlrq;
	private String ismodify;
	private String fgmjyqd;
	private String sflmzh;
	
	private String report_status;
	private String jgdm;
	
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public String getFgmjyqd() {
		return fgmjyqd;
	}

//	public String getLbhdate() {
//		return lbhdate;
//	}
//	public void setLbhdate(String lbhdate) {
//		this.lbhdate = lbhdate;
//	}

	public void setFgmjyqd(String fgmjyqd) {
		this.fgmjyqd = fgmjyqd;
	}



	public String getSflmzh() {
		return sflmzh;
	}



	public void setSflmzh(String sflmzh) {
		this.sflmzh = sflmzh;
	}



	public String getKhdqdm() {
		return khdqdm;
	}



	public void setKhdqdm(String khdqdm) {
		this.khdqdm = khdqdm;
	}

	private String khdqdm;
	// Constructors

	/** default constructor */
	public BaseAmsDszh() {
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

	public String getCkryb() {
		return ckryb;
	}

	public void setCkryb(String ckryb) {
		this.ckryb = ckryb;
	}

	public String getCkrdz() {
		return ckrdz;
	}

	public void setCkrdz(String ckrdz) {
		this.ckrdz = ckrdz;
	}

	public String getCkrdh() {
		return ckrdh;
	}

	public void setCkrdh(String ckrdh) {
		this.ckrdh = ckrdh;
	}

	public String getDlrmc() {
		return dlrmc;
	}

	public void setDlrmc(String dlrmc) {
		this.dlrmc = dlrmc;
	}

	public String getDlrsfzjzl() {
		return dlrsfzjzl;
	}

	public void setDlrsfzjzl(String dlrsfzjzl) {
		this.dlrsfzjzl = dlrsfzjzl;
	}

	public String getDlrsfzjhm() {
		return dlrsfzjhm;
	}

	public void setDlrsfzjhm(String dlrsfzjhm) {
		this.dlrsfzjhm = dlrsfzjhm;
	}

	public String getDlrgjdq() {
		return dlrgjdq;
	}

	public void setDlrgjdq(String dlrgjdq) {
		this.dlrgjdq = dlrgjdq;
	}

	public String getDlrdh() {
		return dlrdh;
	}

	public void setDlrdh(String dlrdh) {
		this.dlrdh = dlrdh;
	}

	public String getJrjgbm() {
		return jrjgbm;
	}

	public void setJrjgbm(String jrjgbm) {
		this.jrjgbm = jrjgbm;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getZhzl() {
		return zhzl;
	}

	public void setZhzl(String zhzl) {
		this.zhzl = zhzl;
	}
//
//	public String getKh() {
//		return kh;
//	}
//
//	public void setKh(String kh) {
//		this.kh = kh;
//	}
//
//	public String getKdqr() {
//		return kdqr;
//	}
//
//	public void setKdqr(String kdqr) {
//		this.kdqr = kdqr;
//	}
//
//	public String getZhjz() {
//		return zhjz;
//	}
//
//	public void setZhjz(String zhjz) {
//		this.zhjz = zhjz;
//	}
//
//	public String getXkrq() {
//		return xkrq;
//	}
//
//	public void setXkrq(String xkrq) {
//		this.xkrq = xkrq;
//	}
//
//	public String getKzt() {
//		return kzt;
//	}
//
//	public void setKzt(String kzt) {
//		this.kzt = kzt;
//	}

	public String getZhlx() {
		return zhlx;
	}

	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}

	public String getYlzhzh() {
		return ylzhzh;
	}

	public void setYlzhzh(String ylzhzh) {
		this.ylzhzh = ylzhzh;
	}

	public String getYlzhjgbm() {
		return ylzhjgbm;
	}

	public void setYlzhjgbm(String ylzhjgbm) {
		this.ylzhjgbm = ylzhjgbm;
	}

	public String getKhrq() {
		return khrq;
	}

	public void setKhrq(String khrq) {
		this.khrq = khrq;
	}

	public String getXhrq() {
		return xhrq;
	}

	public void setXhrq(String xhrq) {
		this.xhrq = xhrq;
	}

	public String getZhzt() {
		return zhzt;
	}

	public void setZhzt(String zhzt) {
		this.zhzt = zhzt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfjrbzk() {
		return sfjrbzk;
	}

	public void setSfjrbzk(String sfjrbzk) {
		this.sfjrbzk = sfjrbzk;
	}

	public String getSfshbzk() {
		return sfshbzk;
	}

	public void setSfshbzk(String sfshbzk) {
		this.sfshbzk = sfshbzk;
	}

	public String getHsjg() {
		return hsjg;
	}

	public void setHsjg(String hsjg) {
		this.hsjg = hsjg;
	}

	public String getWfhsyy() {
		return wfhsyy;
	}

	public void setWfhsyy(String wfhsyy) {
		this.wfhsyy = wfhsyy;
	}

	public String getCzff() {
		return czff;
	}

	public void setCzff(String czff) {
		this.czff = czff;
	}

	public String getXxlx() {
		return xxlx;
	}

	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}

	public String getKhqd() {
		return khqd;
	}

	public void setKhqd(String khqd) {
		this.khqd = khqd;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}

	public BaseAmsDszh(String ckrxm, String ckrsfzjzl, String ckrsfzjhm, String sfzjdqr, String dqdm, String ckrlb,
			String ckrgjdq, String ckrxb, String ckryb, String ckrdz, String ckrdh, String dlrmc, String dlrsfzjzl,
			String dlrsfzjhm, String dlrgjdq, String dlrdh, String jrjgbm, String zh, String zhzl, String zhlx,
			String ylzhzh, String ylzhjgbm, String khrq, String xhrq, String zhzt, String bz, String sfjrbzk,
			String sfshbzk, String hsjg, String wfhsyy, String czff, String xxlx, String khqd, String remarks,
			String jlzt, String reserve4, String reserve5, String jlrq, String ismodify, String fgmjyqd, String sflmzh,
			String khdqdm, String report_status, String jgdm) {
		super();
		
		this.ckrxm = ckrxm;
		this.ckrsfzjzl = ckrsfzjzl;
		this.ckrsfzjhm = ckrsfzjhm;
		this.sfzjdqr = sfzjdqr;
		this.dqdm = dqdm;
		this.ckrlb = ckrlb;
		this.ckrgjdq = ckrgjdq;
		this.ckrxb = ckrxb;
		this.ckryb = ckryb;
		this.ckrdz = ckrdz;
		this.ckrdh = ckrdh;
		this.dlrmc = dlrmc;
		this.dlrsfzjzl = dlrsfzjzl;
		this.dlrsfzjhm = dlrsfzjhm;
		this.dlrgjdq = dlrgjdq;
		this.dlrdh = dlrdh;
		this.jrjgbm = jrjgbm;
		this.zh = zh;
		this.zhzl = zhzl;
		this.zhlx = zhlx;
		this.ylzhzh = ylzhzh;
		this.ylzhjgbm = ylzhjgbm;
		this.khrq = khrq;
		this.xhrq = xhrq;
		this.zhzt = zhzt;
		this.bz = bz;
		this.sfjrbzk = sfjrbzk;
		this.sfshbzk = sfshbzk;
		this.hsjg = hsjg;
		this.wfhsyy = wfhsyy;
		this.czff = czff;
		this.xxlx = xxlx;
		this.khqd = khqd;
		this.remarks = remarks;
		this.jlzt = jlzt;
		this.reserve4 = reserve4;
		this.reserve5 = reserve5;
		this.jlrq = jlrq;
		this.ismodify = ismodify;
		this.fgmjyqd = fgmjyqd;
		this.sflmzh = sflmzh;
		this.khdqdm = khdqdm;
		this.report_status = report_status;
		this.jgdm = jgdm;
	}



	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getJlrq() {
		return jlrq;
	}

	public void setJlrq(String jlrq) {
		this.jlrq = jlrq;
	}

	public String getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(String ismodify) {
		this.ismodify = ismodify;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;

	}

}