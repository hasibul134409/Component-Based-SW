package client;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import customer.CustomerManagementRemote;
import customer.data.Address;
import customer.data.Customer;
import order.OrderManagementRemote;
import order.data.Order;
import stock.StockManagementRemote;



/**
 * Simple Client Application to test the PrintingShopCRM
 * 
 * Calls the three component beans through their automatic generated ejb JNDI names
 * then for each bean call a simple test scenario
 * 
 * SetUp: 	Please do not forget to include jboss-client.jar in your projects build path!
 * 			Without it all EJB libraries and functionality will be unknown to the project. 
 * 			For details see the footnotes in the Exercise 3 Task sheet
 *
 */
public class ClientMain {
	
	/**
	 * Attributes to save the looked up Remote Beans Interfaces
	 */
	protected static CustomerManagementRemote customerbean;
	protected static OrderManagementRemote orderbean;
	protected static StockManagementRemote stockbean;
	
	
	/**
	 * Look up the Remote Bean Interfaces and calls a test scenario. Last, clean up the server.
	 * The test scenarios output can be found in the console.
	 * 
	 * Each test scenario has 4 Parts:
	 * (1) Create some data and save it in the Bean database
	 * (2) Find one Data set by id
	 * (3) Change the found Data set and update it in the Bean database
	 * (3) Remove the found data set from the Bean database
	 * (*) Between the steps, look up all data from the Bean database
	 */
	public static void main(String[] args)  {
		
		// configure the properties for the Application Context
		Properties props = new Properties();
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
	        String name = "ejb:/PrintingShopCRM//CustomerManagement!customer.CustomerManagementRemote";
	        customerbean = (CustomerManagementRemote)context.lookup(name);
	        testCustomer();
		}catch(Exception e){
			System.out.println("Errors in Customer Management");
			e.printStackTrace();
			return;
		}
		
		// use the Context to look up the OrderManagement Bean on the server
	    // run test scenario
		try{   
	        String name = "ejb:/PrintingShopCRM//OrderManagement!order.OrderManagementRemote?stateful";
	        orderbean = (OrderManagementRemote)context.lookup(name);
	        testOrder();
		}catch(Exception e){
			System.out.println("Errors in Order Management");
			e.printStackTrace();
			return;
		}
		
		// use the Context to look up the StockManagment Bean on the server
	    // run test scenario
		try{   
	        String name = "ejb:/PrintingShopCRM//StockManagement!stock.StockManagementRemote?stateful";
	        stockbean = (StockManagementRemote)context.lookup(name);
	        testStock();
		}catch(Exception e){
			System.out.println("Errors in Stock Management");
			e.printStackTrace();
			return;
		}
		
		//clean up to prevent amass objects if doing multiple runs while the server is running
		cleanUp();
		
	}

	/**
	 * Clean Up routine
	 * Deletes all data in all three component beans
	 */
	private static void cleanUp() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("------------------------------");
		System.out.println("Start Clean Up");
		//clean up customers
		Collection<Customer> resultC = customerbean.listCustomers();
		for(Customer c: resultC) {
			customerbean.removeCustomer(c.getId());
		}
		//clean up orders
		Collection<Order> resultO = orderbean.listOrders();
		for (Order o : resultO) {
			orderbean.removeOrder(o.getId());
		}
		//clean up products
		Collection<Map<String, String>> resultP = stockbean.listStock();
		for (Map<String, String> p : resultP) {
			stockbean.removeStock(Long.parseLong(p.get("id")));
		}
		System.out.println("Finished Clean Up");
		System.out.println("------------------------------");
		
	}

	/**
	 * Test scenario for the CustomerManagement Bean component
	 * Output is send to Console
	 * After running three Customers exists which are used in the Order test scenario
	 * 
	 * Each test scenario has 4 Parts:
	 * (1) Create some data and save it in the Bean database
	 * (2) Find one Data set by id
	 * (3) Change the found Data set and update it in the Bean database
	 * (3) Remove the found data set from the Bean database
	 * (*) Between the steps, look up all data from the Bean database
	 */
	private static void testCustomer() {
		System.out.println("------------------------------");
		System.out.println("Start Test Customer Component:");
		System.out.println("------------------------------");
		System.out.println("(1) Add 4 Customers!");
		customerbean.addOrUpdate(new Customer("Max Musterman", new Address("Musterstraﬂe 1", "Musterhausen", "01234")));
		customerbean.addOrUpdate(new Customer("Eva Musterfrau", new Address("Musterstraﬂe 2", "Musterhausen", "01234")));
		customerbean.addOrUpdate(new Customer("John Johnsen ", new Address("Johnsonstreet 2", "NY", "01234")));
		customerbean.addOrUpdate(new Customer("Juli Johnson", new Address("Johnsenstreet 3", "WDC", "01234")));
		System.out.println("List the Customers:");
		Collection<Customer> result = customerbean.listCustomers();
		for(Customer c: result) {
			System.out.println(c.toString());
		}
		System.out.println("------------------------------");
		if(!result.isEmpty()) {
			System.out.println("(2) Find the first Customer:");
			Customer c  = customerbean.findCustomer(result.iterator().next().getId());
			System.out.println(c);
			System.out.println("------------------------------");
			System.out.println("(3) Change the first Customer in Database:");
			c.setName("Karl Mustermann");
			customerbean.addOrUpdate(c);
			System.out.println("List the Customers:");
			result = customerbean.listCustomers();
			for(Customer c1: result) {
				System.out.println(c1.toString());
			}
			System.out.println("------------------------------");
			System.out.println("(4) Remove the first Customer from Database:");
			Customer d  = customerbean.findCustomer(c.getId());
			customerbean.removeCustomer(d.getId());
			System.out.println("List the Customers:");
			result = customerbean.listCustomers();
			for(Customer c2: result) {
				System.out.println(c2.toString());
			}
		}
		System.out.println("------------------------------");
		System.out.println("End Test Customer Component");
		System.out.println("------------------------------");
		
		
		
	}
	
	/**
	 * Test scenario for the OrderManagement Bean component
	 * Output is send to Console
	 * After running two Orders exists which are used in the Stock test scenario
	 * 
	 * Each test scenario has 4 Parts:
	 * (1) Create some data and save it in the Bean database
	 * (2) Find one Data set by id
	 * (3) Change the found Data set and update it in the Bean database
	 * (3) Remove the found data set from the Bean database
	 * (*) Between the steps, look up all data from the Bean database
	 */
	private static void testOrder() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("------------------------------");
		System.out.println("Start Test Order Component:");
		System.out.println("------------------------------");
		System.out.println("(1) Add three Orders!");
		Collection<Customer> r = customerbean.listCustomers();
		Iterator<Customer> itr = r.iterator();
		for(int i = 0; i < 3; i++) {
			if(itr.hasNext()) {
				Order o = new Order(itr.next().getId());
				o.addJob("print", "Hello World!");
				orderbean.addOrUpdate(o);
			}
		}
		System.out.println("List the Orders:");
		Collection<Order> result = orderbean.listOrders();
		for(Order o: result) {
			System.out.println(o.toString());
		}
		System.out.println("------------------------------");
		if(!result.isEmpty()) {
			System.out.println("(2) Find the first Order:");
			Order c  = orderbean.findOrder(result.iterator().next().getId());
			System.out.println(c);
			System.out.println("------------------------------");
			System.out.println("(3) Change the first Order in Database:");
			c.addJob("polish", "finest");
			c.createInvoice();
			c.getInvoce().setDate(LocalDateTime.now().plusDays(10));
			orderbean.addOrUpdate(c);
			System.out.println("List the Orders:");
			result = orderbean.listOrders();
			for(Order c1: result) {
				System.out.println(c1.toString());
			}
			System.out.println("------------------------------");
			System.out.println("(4) Remove the first Order from Database:");
			Order d  = orderbean.findOrder(c.getId());
			orderbean.removeOrder(d.getId());
			System.out.println("List the Orders:");
			result = orderbean.listOrders();
			for(Order c2: result) {
				System.out.println(c2.toString());
			}
		}
		System.out.println("------------------------------");
		System.out.println("End Test Order Component");
		System.out.println("------------------------------");
		
		
		
	}
	
	/**
	 * Test scenario for the StockManagement Bean component
	 * Output is send to Console
	 * 
	 * Each test scenario has 4 Parts:
	 * (1) Create some data and save it in the Bean database
	 * (2) Find one Data set by id
	 * (3) Change the found Data set and update it in the Bean database
	 * (3) Remove the found data set from the Bean database
	 * (*) Between the steps, look up all data from the Bean database
	 */
	private static void testStock() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("------------------------------");
		System.out.println("Start Test Stock Component:");
		System.out.println("------------------------------");
		System.out.println("(1) Add two Products!");
		Collection<Order> r = orderbean.listOrders();
		Iterator<Order> itr = r.iterator();
		for(int i = 0; i < 2; i++) {
			if(itr.hasNext()) {
				Map<String,String> product = new HashMap<String,String>();
				product.put("orderid", ""+itr.next().getId());
				stockbean.addOrUpdate(product);
			}
		}
		System.out.println("List the Products:");
		Collection<Map<String, String>> result = stockbean.listStock();
		for(Map<String, String> p: result) {
			System.out.println("[Product] " + "ID: " + p.get("id") + "; " + "OID: " + p.get("orderid") + "; " + "FINISHED: " + p.get("isfinished"));
		}
		System.out.println("------------------------------");
		if(!result.isEmpty()) {
			System.out.println("(2) Find the first Product:");
			Map<String, String> p  = stockbean.findStock(Long.parseLong(result.iterator().next().get("id")));
			System.out.println("[Product] " + "ID: " + p.get("id") + "; " + "OID: " + p.get("orderid") + "; " + "FINISHED: " + p.get("isfinished"));
			System.out.println("------------------------------");
			System.out.println("(3) Change the first Product in Database:");
			p.put("isfinished", "true");
			stockbean.addOrUpdate(p);
			System.out.println("List the Products:");
			result = stockbean.listStock();
			for(Map<String, String> p1: result) {
				System.out.println("[Product] " + "ID: " + p1.get("id") + "; " + "OID: " + p1.get("orderid") + "; " + "FINISHED: " + p1.get("isfinished"));
			}
			System.out.println("------------------------------");
			System.out.println("(4) Remove the first Product from Database:");
			Map<String, String> d  = stockbean.findStock(Long.parseLong(p.get("id")));
			stockbean.removeStock(Long.parseLong(d.get("id")));
			System.out.println("List the Products:");
			result = stockbean.listStock();
			for(Map<String, String> p2: result) {
				System.out.println("[Product] " + "ID: " + p2.get("id") + "; " + "OID: " + p2.get("orderid") + "; " + "FINISHED: " + p2.get("isfinished"));
			}
		}
		System.out.println("------------------------------");
		System.out.println("End Test Stock Component");
		System.out.println("------------------------------");
		
	}

}
