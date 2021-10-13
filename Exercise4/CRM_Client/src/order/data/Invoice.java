package order.data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Copy of the Invoice Implementation for the Client
 * Needed since the remote interface returns a Java Object of this type
 * Is not need to be an exact copy of the Invoice that is used in the bean (see PrintingShopCRM)
 * Should only reveal necessary information.
 * Must be implementing the Serializable interface to be received and send back to the bean.
 *
 */
public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected long orderid;
	protected LocalDateTime date;
	
	
	public Invoice(long orderid) {
		this.orderid = orderid;
		this.date = LocalDateTime.now();
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
