package order.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Simple Order datatype implemented as a normal Java class.
 * Implements the Serializeable Interface so it can be sent remotely to other beans/clients.
 * Please see the notes on the constructors for information on usage.
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long id;
	
	protected long customerId;
	protected List<Job> jobList;
	protected Invoice invoce;
	
	/**
	 * constructor for temporary objects without id
	 */
	public Order(long customerId) {
		this.customerId = customerId;
		this.jobList = new ArrayList<Job>();
	}
	
	/**
	 * constructor for generating an "persistent" object for the inMemory storage out of an temporary Order.
	 * Has an id greater 0.
	 */
	public Order(long orderid , Order original) {
		this.id = orderid;
		this.customerId = original.getCustomerId();
		if(original.getInvoce() == null) {
			this.invoce = null;
		}else {
			this.invoce = new Invoice(original.getInvoce());
		}
		this.jobList = new ArrayList<Job>();
		for(Job j : original.getJobList()) {
			this.jobList.add(new Job(j));
		}
		
	}
	
	/**
	 * Copy constructor. Important since we use in-memory storage based on Java collections.
     * Creates a copy that is save to give out. A copy can be changed without changing the object in the storage.
     */
	public Order(Order original) {
		this.id = original.getId();
		this.customerId = original.getCustomerId();
		if(original.getInvoce() == null) {
			this.invoce = null;
		}else {
			this.invoce = new Invoice(original.getInvoce());
		}
		this.jobList = new ArrayList<Job>();
		for(Job j : original.getJobList()) {
			this.jobList.add(new Job(j));
		}
		
	}
	
	

	public List<Job> getJobList() {
		return jobList;
	}

	public Invoice getInvoce() {
		return invoce;
	}

	public void setInvoce(Invoice invoce) {
		this.invoce = invoce;
	}

	public long getId() {
		return id;
	}

	public long getCustomerId() {
		return customerId;
	}
	
	public boolean addJob(String description, String parameter){
		return this.jobList.add(new Job(description,parameter));
	}
	
	public boolean createInvoice() {
		if(this.invoce != null || this.id == 0) {
			return false;
		}else{
			this.invoce = new Invoice(this.id);
			return true;
		}
	}
	
	@Override
	public String toString() {
		String result = "[Order] " +
						"ID: " + this.id +
						"CID: " +this.customerId + "; " +
						"INVOICE:" + this.invoce + "; " +
						"JOBS: ";
		for(Job j : this.jobList) {
			result += j + " ";
		}
						
		return result;				
						
	}

}
