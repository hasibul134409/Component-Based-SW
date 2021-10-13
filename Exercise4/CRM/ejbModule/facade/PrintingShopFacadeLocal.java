package facade;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;

import customer.data.Customer;
import order.data.Order;

@Local
public interface PrintingShopFacadeLocal {
	
	public Customer findCustomer( long customerId);
	
	public void addOrUpdate(Customer customer);
	
	public Collection<Customer> listCustomers();
	
	public void removeCustomer(long customerId);
	
	public Order findOrder( long orderId);
	
	public void addOrUpdate(Order order);
	
	public Collection<Order> listOrders();
	
	public void removeOrder(long orderId);
		
	public Map<String,String> findStock( long stockId);
	
	public void addOrUpdate( Map<String, String> stock);
	
	public Collection<Map<String,String>> listStock();
	
	public void removeStock(long stockId);

}
