package resource.bean.pub.base;

import resource.bean.pub.Bctl;

public class BaseBrnoJbcdLink {
	private String brno;
	private String jrjgbm;
	
	public String getBrno() {
		return brno;
	}
	public void setBrno(String brno) {
		this.brno = brno;
	}
	public String getJrjgbm() {
		return jrjgbm;
	}
	public void setJrjgbm(String jrjgbm) {
		this.jrjgbm = jrjgbm;
	}
	
	@Override
	public String toString() {
		return "BaseBrnoJbcdLink [brno=" + brno + ", jrjgbm=" + jrjgbm + "]";
	}
	
}
