package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmpId entity provides the base persistence
 * definition of the EastGrhqckfhzmxjlTmpId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class BaseAmsFjmzhId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8039505415290666133L;
	private String ind_idtype;
	private String ind_idnumber;

	// Constructors

	/** default constructor */
	public BaseAmsFjmzhId() {
	}

	public String getInd_idtype() {
		return ind_idtype;
	}

	public BaseAmsFjmzhId(String ind_idtype, String ind_idnumber) {
		super();
		this.ind_idtype = ind_idtype;
		this.ind_idnumber = ind_idnumber;
	}

	public void setInd_idtype(String ind_idtype) {
		this.ind_idtype = ind_idtype;
	}

	public String getInd_idnumber() {
		return ind_idnumber;
	}

	public void setInd_idnumber(String ind_idnumber) {
		this.ind_idnumber = ind_idnumber;
	}
}