package customer;

import java.util.Collection;

import javax.ejb.Local;

import customer.data.Customer;


/**
 * Local Interface for the CustomerManagment Bean.
 * Can be used from beans in the same module/container.
 * Can have other/more methods/options then the remote one.
 */
@Local
public interface CustomerManagementLocal {
	
	public Customer findCustomer( long customerId);
	
	public void addOrUpdate(Customer customer);
	
	public Collection<Customer> listCustomers();

	public void removeCustomer(long customerId);
	
}
