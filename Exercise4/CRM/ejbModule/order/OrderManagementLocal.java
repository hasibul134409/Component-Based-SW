package order;

import java.util.Collection;

import javax.ejb.Local;

import order.data.Order;

/**
 * Local Interface for the OrderManagment Bean.
 * Can be used from beans in the same module/container.
 * Can have other/more methods/options then the remote one.
 */
@Local
public interface OrderManagementLocal {
	
	public Order findOrder( long orderId);
	
	public void addOrUpdate(Order order);
	
	public Collection<Order> listOrders();
	
	public void removeOrder(long orderId);

}
