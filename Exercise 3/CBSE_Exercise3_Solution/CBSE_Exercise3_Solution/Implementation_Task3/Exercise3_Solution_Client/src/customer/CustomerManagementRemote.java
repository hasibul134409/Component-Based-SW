package customer;

import java.util.Collection;

import javax.ejb.Remote;

import customer.data.Customer;

/**
 * Copy of the remote interface of the CustomerManagement bean.
 * Needed for the client to know and use the bean methods.
 *
 */
@Remote
public interface CustomerManagementRemote {
	
	public Customer findCustomer( long customerId);
	
	public void addOrUpdate(Customer customer);
	
	public Collection<Customer> listCustomers();
	
	public void removeCustomer(long customerId);

}
