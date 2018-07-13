package resources.east.data.base;

public class BaseAmsDszhMessageInfo implements java.io.Serializable{
	private Integer id;
	private String messagetype;
	private String messagename;
	private String datadate;
	private String borndate;
	private String messagestatus;
	private String reserve;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getMessagename() {
		return messagename;
	}
	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}
	public String getDatadate() {
		return datadate;
	}
	public void setDatadate(String datadate) {
		this.datadate = datadate;
	}
	public String getBorndate() {
		return borndate;
	}
	public void setBorndate(String borndate) {
		this.borndate = borndate;
	}
	public String getMessagestatus() {
		return messagestatus;
	}
	public void setMessagestatus(String messagestatus) {
		this.messagestatus = messagestatus;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
	
}
