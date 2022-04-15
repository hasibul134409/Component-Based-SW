package order;

import java.util.Collection;

import javax.ejb.Remote;

import order.data.Order;

/**
 * Copy of the remote interface of the OrderManagement bean.
 * Needed for the client to know and use the bean methods.
 *
 */
@Remote
public interface OrderManagementRemote {
	
	public Order findOrder( long orderId);
	
	public void addOrUpdate(Order order);
	
	public Collection<Order> listOrders();
	
	public void removeOrder(long orderId);

}
