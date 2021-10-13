package order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import order.data.Order;

/**
 * Stateful Session Bean OrderManagement (@Stateful).
 * Implementation of the Order Management component of the C/D-Model (Exercise 2).
 * Implemented as a stateful bean that saves the Orders in a in-memory storage based on Java collections.
 * 
 * Please note the usage of data copies when giving data out of the storage.
 * This is necessary since java works on references, meaning that without copies the data in storage can be changed from the outside our component. 
 * In our case, local java calls could cause this problem. 
 * Remote calls should be fine since the objects are serialized anyway.
 * This concept is important in any component system that uses a reference-based programming language.
 */
@Stateful
@LocalBean
public class OrderManagement implements OrderManagementRemote, OrderManagementLocal {

	/**
	 * in-memory storage of Orders.
	 * Based on a Java Map with the order id as key and the order as value.
	 */
	Map<Long, Order> orderMap = new HashMap<Long, Order>();
	
	/**
	 * Generator for order ids.
	 * use the incrementAndGet function to generate a new id
	 */
	protected static AtomicLong idGenerator = new AtomicLong(0);
	
    /**
     * Default constructor. 
     */
    public OrderManagement() {
        // TODO Auto-generated constructor stub
    }

    /**
     * gives out a copy of the found order that has the right id
     */
	@Override
	public Order findOrder(long orderId) {
		Order result = this.orderMap.get(orderId);
		if(result == null) {
			return null;
		}else {
			return new Order(result);
		}
		
	}

	/**
	 * Saves the Order in the data structure if it has an id
	 * Else the order is  a new temporary order and is saved under a new id 
	 */
	@Override
	public void addOrUpdate(Order order) {
		if(order != null) {
			Order temp;
			if(order.getId() == 0) {
				temp = new Order(idGenerator.incrementAndGet(), order);	
			}else {
				temp = order;
			}
			this.orderMap.put( temp.getId(), temp);
		}
		
		
	}

	/**
	 * returns a complete collection of order copies from the storage
	 */
	@Override
	public Collection<Order> listOrders() {
		List<Order> result = new ArrayList<Order>();
		for(Order o :this.orderMap.values()) {
			result.add(new Order(o));
		}
		return result;
	}

	@Override
	public void removeOrder(long orderId) {
		this.orderMap.remove(orderId);
		
	}

}
