package facade;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import customer.CustomerManagementRemote;
import customer.CustomerManagementLocal;
import customer.data.Customer;
import order.OrderManagementRemote;
import order.OrderManagementLocal;
import order.data.Order;
import stock.StockManagementRemote;
import stock.StockManagementLocal;

/**
 * Session Bean implementation class PrintingShopFacade
 */
@Stateless
@LocalBean
public class PrintingShopFacade implements PrintingShopFacadeRemote, PrintingShopFacadeLocal {

    /**
     * Default constructor. 
     */
	
	protected static CustomerManagementLocal customerbean;
	protected static OrderManagementLocal orderbean;
	protected static StockManagementLocal stockbean;
	protected static PrintingShopProductionFacadeRemote productionbean;
	
    public PrintingShopFacade() {
    	Properties props = new Properties();
    	props.put("java.naming.factory.initial","org.jboss.as.naming.InitialContextFactory");
        props.put("java.naming.provider.url", "localhost");
        props.put("jboss.naming.client.ejb.context", "true");
	    props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	    InitialContext context;
	    
	    // Create the EJB Application context
	    try {
			context = new InitialContext(props);
		} catch (NamingException e) {
			System.out.println("Context could not be created!");
			e.printStackTrace();
			return;
		}
		
	    // use the Context to look up the CustomerManagement Bean on the server
	    // run test scenario
		try{   
			String name = "java:module/CustomerManagement!customer.CustomerManagementLocal";
	        customerbean = (CustomerManagementLocal)context.lookup(name);
	       // testCustomer();
		}catch(Exception e){
			System.out.println("Errors in Customer Management");
			e.printStackTrace();
			return;
		}
		
		// use the Context to look up the OrderManagement Bean on the server
	    // run test scenario
		try{   
			String name = "java:module/OrderManagement!order.OrderManagementLocal";
	        orderbean = (OrderManagementLocal)context.lookup(name);
	        //testOrder();
		}catch(Exception e){
			System.out.println("Errors in Order Management");
			e.printStackTrace();
			return;
		}
		
		// use the Context to look up the StockManagment Bean on the server
	    // run test scenario
		try{   
			String name = "java:module/StockManagement!stock.StockManagementLocal";
	        stockbean = (StockManagementLocal)context.lookup(name);
	        //testStock();
		}catch(Exception e){
			System.out.println("Errors in Stock Management");
			e.printStackTrace();
			return;
		}
		
		try {
    		String name = "java:global/PrintingShopProduction/PrintingShopProductionFacade!facade.PrintingShopProductionFacadeRemote";
    		productionbean = (PrintingShopProductionFacadeRemote)context.lookup(name);
    	}catch(Exception e) {
    		System.out.println("Errors in Facade Factory Management");
			e.printStackTrace();
			return;
    	}
		
		//clean up to prevent amass objects if doing multiple runs while the server is running
		//cleanUp();
    }

	@Override
	public Customer findCustomer(long customerId) {
		return customerbean.findCustomer(customerId);
	}

	@Override
	public void addOrUpdate(Customer customer) {
		customerbean.addOrUpdate(customer);
	}

	@Override
	public Collection<Customer> listCustomers() {
		return customerbean.listCustomers();
	}

	@Override
	public void removeCustomer(long customerId) {
		customerbean.removeCustomer(customerId);
	}

	@Override
	public Order findOrder(long orderId) {
		return orderbean.findOrder(orderId);
	}

	@Override
	public void addOrUpdate(Order order) {
		orderbean.addOrUpdate(order);
		Customer customer = customerbean.findCustomer(order.getCustomerId());
		if(customer == null || !customer.isApproved()) {
			return;
		}
		this.productionbean.startProduction();
	}

	@Override
	public Collection<Order> listOrders() {
		return orderbean.listOrders();
	}

	@Override
	public void removeOrder(long orderId) {
		orderbean.removeOrder(orderId);
	}

	@Override
	public Map<String, String> findStock(long stockId) {
		return stockbean.findStock(stockId);
	}

	@Override
	public void addOrUpdate(Map<String, String> stock) {
		stockbean.addOrUpdate(stock);
	}

	@Override
	public Collection<Map<String, String>> listStock() {
		return stockbean.listStock();
	}

	@Override
	public void removeStock(long stockId) {
		stockbean.removeStock(stockId);
	}

}
