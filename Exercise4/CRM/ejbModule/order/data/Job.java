package order.data;

import java.io.Serializable;

/** 
 * Simple Job datatype implemented as a normal Java class.
 * Implements the Serializeable Interface so it can be sent remotely to other beans/clients.
 * Please see the notes on the constructors for information on usage.
 */
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String jobDescription;
	protected String parameter;
	
	/**
	 * normal constructor
	 */
	public Job(String jobDescription, String parameter) {
		this.jobDescription = jobDescription;
		this.parameter = parameter;
	}
	
	/**
	 * Copy constructor. Important since we use in-memory storage based on Java collections.
     * Creates a copy that is save to give out. A copy can be changed without changing the object in the storage.
     */
	public Job(Job original) {
		this.jobDescription = original.getJobDescription();
		this.parameter = original.getParameter();
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getParameter() {
		return parameter;
	}
	
	@Override
	public String toString() {
		return "[Job] " + "Desc: " + this.jobDescription + " : " + this.parameter;
	}

	
}
