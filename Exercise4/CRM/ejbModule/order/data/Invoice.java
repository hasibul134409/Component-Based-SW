package order.data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** 
 * Simple Invoice datatype implemented as a normal Java class.
 * Implements the Serializeable Interface so it can be sent remotely to other beans/clients.
 * Please see the notes on the constructors for information on usage.
 */
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long orderid;
	protected LocalDateTime date;
	
	
	/**
	 * normal constructor
	 */
	public Invoice(long orderid) {
		this.orderid = orderid;
		this.date = LocalDateTime.now();
	}
	
	/**
	 * Copy constructor. Important since we use in-memory storage based on Java collections.
     * Creates a copy that is save to give out. A copy can be changed without changing the object in the storage.
     */
	public Invoice(Invoice invoice) {
		this.orderid = invoice.getOrderid();
		// LocalDateTime is immutable, no copy needed
		this.date = invoice.date;
		
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public long getOrderid() {
		return orderid;
	}
	
	@Override
	public String toString() {
		return "[INVOICE] " + "DATE: " + this.date.toString(); 
	}

}
