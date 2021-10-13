package order.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copy of the Order Implementation for the Client
 * Needed since the remote interface returns a Java Object of this type
 * Is not need to be an exact copy of the Order that is used in the bean (see PrintingShopCRM)
 * Should only reveal necessary information.
 * Must be implementing the Serializable interface to be received and send back to the bean.
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long id;
	
	protected long customerId;
	protected List<Job> jobList;
	protected Invoice invoce;
	
	public Order(long customerId) {
		this.customerId = customerId;
		this.jobList = new ArrayList<Job>();
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
						"ID: " + this.id + "; " +
						"CID: " +this.customerId + "; " +
						"INVOICE:" + this.invoce + "; " +
						"JOBS: ";
		for(Job j : this.jobList) {
			result += j + " ";
		}
						
		return result;				
						
	}

}
