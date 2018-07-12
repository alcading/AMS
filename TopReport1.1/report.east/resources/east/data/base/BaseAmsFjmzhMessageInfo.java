package resources.east.data.base;

import java.math.BigDecimal;

/**
 * AbstractEastGrhqckfhzmxjlTmp entity provides the base persistence definition
 * of the EastGrhqckfhzmxjlTmp entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAmsFjmzhMessageInfo implements java.io.Serializable {


	public BaseAmsFjmzhMessageInfo(boolean select, Integer miId, String messageType, String messageName,
			String importDate, String reserve) {
		super();
		this.select = select;
		this.miId = miId;
		this.messageType = messageType;
		this.messageName = messageName;
		this.importDate = importDate;
		this.reserve = reserve;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7981634766426960189L;
	
	  private boolean select;
	  private Integer miId;
	  private String messageType;
	  private String messageName;
	  private String importDate;
	  private String reserve;
	  
	public BaseAmsFjmzhMessageInfo(){}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public Integer getMiId() {
		return miId;
	}

	public void setMiId(Integer miId) {
		this.miId = miId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	  

}