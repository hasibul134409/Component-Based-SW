package customer.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/** 
 * Simple Address datatype implemented as an Entity Bean (@Entity).
 * The used Database needs to be configured in META-INF/persistenz.xml
 * Implements the Serializeable Interface so it can be sent remotely to other beans/clients. 
 * Its Id is marked as the foreign key with @Id and is automatically generated by the time of its persistence (@GeneratedValue).
 */
@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Automatic generated foreign key by the time of persistence.
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected long id;
	
	protected String street;
	protected String city;
	protected String zipCode;
	
	/**
	 * needed for JPA
	 */
	public Address(){}
	
	/**
	 * normal constructor
	 */
	public Address(String street, String city, String zipCode) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	/**
	 * copy constructor, not really needed since we using a persistent Database, but good to have.
	 */
	public Address(Address original) {
		this.street = original.getStreet();
		this.city = original.getCity();
		this.zipCode = original.getZipCode();
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
