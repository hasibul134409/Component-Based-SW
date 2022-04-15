package order;

import java.util.Collection;

import javax.ejb.Remote;

import order.data.Order;

/**
 * Remote Interface for the OrderManagment Bean.
 * 
 * A copy of this interface must be provided to the client that want to use the bean.
 * The copy can be provided as a .java, .class, or in a .jar file. 
 * The provided file must be included in the client project (with same package structure).
 * 
 * Please note that this Interface sends out Java Objects.
 * The Java Objects must implement the Serializeable Interface (see JavaDoc in the Classes).
 * This means that a copy of the Java Class must be provided to the user.
 * The copy can be provided as a .java, .class, or in a .jar file. 
 * The provided file must be included in the client project (with same package structure).
 * The copy must not be an exact copy but should only include necessary features. (see provided test client)
 * 
 */
@Remote
public interface OrderManagementRemote {
	
	public Order findOrder( long orderId);
	
	public void addOrUpdate(Order order);
	
	public Collection<Order> listOrders();
	
	public void removeOrder(long orderId);

}
