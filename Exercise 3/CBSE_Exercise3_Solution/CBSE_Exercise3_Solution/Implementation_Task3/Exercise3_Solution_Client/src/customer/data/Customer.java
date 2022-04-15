package customer.data;

import java.io.Serializable;

/**
 * Copy of the Customer Implementation for the Client
 * Needed since the remote interface returns a Java Object of this type
 * Is not need to be an exact copy of the Customer that is used in the bean (see PrintingShopCRM)
 * Should only reveal necessary information.
 * Must be implementing the Serializable interface to be received and send back to the bean.
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long id;
	
	protected String name;
	protected boolean approved;
	
	protected Address address;
	
	public Customer(String name, Address address) {
		this.name = name;
		this.address = address;
		this.approved = false;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "[Customer]" + " ID: (" + this.id + "); "+ "NAME: " + this.name + "; " + this.address;
	}
	
}
