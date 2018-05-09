package resources.east.data.base;

/**
 * AbstractEastGrhqckfhzmxjlTmpId entity provides the base persistence
 * definition of the EastGrhqckfhzmxjlTmpId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class BaseAmsDszhId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8039505415290666133L;
	private String ckrsfzjzl;
	private String ckrsfzjhm;

	// Constructors

	/** default constructor */
	public BaseAmsDszhId() {
	}

	/** full constructor */
	public BaseAmsDszhId(String ckrsfzjzl, String ckrsfzjhm) {
		this.ckrsfzjzl = ckrsfzjzl;
		this.ckrsfzjhm = ckrsfzjhm;
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


}