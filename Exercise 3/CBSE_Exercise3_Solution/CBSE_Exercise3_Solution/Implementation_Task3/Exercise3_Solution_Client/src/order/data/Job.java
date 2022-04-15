package order.data;

import java.io.Serializable;

/**
 * Copy of the Job Implementation for the Client
 * Needed since the remote interface returns a Java Object of this type
 * Is not need to be an exact copy of the Job that is used in the bean (see PrintingShopCRM)
 * Should only reveal necessary information.
 * Must be implementing the Serializable interface to be received and send back to the bean.
 *
 */
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String jobDescription;
	protected String parameter;
	
	public Job(String jobDescription, String parameter) {
		this.jobDescription = jobDescription;
		this.parameter = parameter;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getParameter() {
		return parameter;
	}
	
	@Override
	public String toString() {
		return "[Job] " + "Desc: " + this.jobDescription + " := " + this.parameter;
	}

	
}
