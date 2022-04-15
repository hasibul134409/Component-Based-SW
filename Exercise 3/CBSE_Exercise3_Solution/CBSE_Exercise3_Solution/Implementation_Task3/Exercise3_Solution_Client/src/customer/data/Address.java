package customer.data;

import java.io.Serializable;

/**
 * Copy of the Address Implementation for the Client
 * Needed since the remote interface returns a Java Object of this type
 * Is not need to be an exact copy of the Address that is used in the bean (see PrintingShopCRM)
 * Should only reveal necessary information.
 * Must be implementing the Serializable interface to be received and send back to the bean.
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long id;
	
	protected String street;
	protected String city;
	protected String zipCode;
	
	public Address(String street, String city, String zipCode) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public String toString() {
		return "ADDRESS: " + this.street + ", " + this.zipCode + ", " + this.city;
	}
}
