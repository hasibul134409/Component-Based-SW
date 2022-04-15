package stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import stock.data.Product;

/**
 * Stateful Session Bean StockManagement (@Stateful).
 * Implementation of the Stock Management component of the C/D-Model (Exercise 2).
 * Implemented as a stateful bean that saves the Products in a in-memory storage based on Java collections.
 * 
 * The dataflow of the product data in and out the component is designed using an en- and decoding from Product to String data.
 * The exact definition of the Product is completely hidden inside of the component.
 * 
 */
@Stateful
@LocalBean
public class StockManagement implements StockManagementRemote, StockManagementLocal {

	/**
	 * in-memory storage of Products.
	 * Based on a Java Map with the order id as key and the order as value.
	 */
	Map<Long, Product> productMap = new HashMap<Long, Product>();
	
	/**
	 * Generator for product ids.
	 * use the incrementAndGet function to generate a new id
	 */
	protected static AtomicLong idGenerator = new AtomicLong(0);
	
    /**
     * Default constructor. 
     */
    public StockManagement() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Gives out a encoded Map of String data of the found product that has the right id
     * Keys are the attribute names and values the corresponding data as Strings
     */
	@Override
	public Map<String, String> findStock(long stockId) {
		 Product result = this.productMap.get(stockId);
		 if(result == null) {
			 return null;
		 }else {
			 Map<String,String> rMap = new HashMap<String, String>();
			 rMap.put("id", ""+result.getId());
			 rMap.put("orderid", ""+result.getOrderid());
			 rMap.put("isfinished", ""+result.isFinished());
			 return rMap;
		 }
	}

	/**
	 * Decodes and saves the Product in the data structure if it has an id in the Map
	 * Else the decoded Product is saved under a new id 
	 */
	@Override
	public void addOrUpdate(Map<String, String> stock) {
		if(stock == null || stock.isEmpty()) {
			return;
		}
		Long id = null;
		if(!stock.containsKey("id") || stock.get("id").isEmpty() || stock.get("id").matches("0") ) {
			id = idGenerator.incrementAndGet();
		}else {
			try {
				id =Long.parseLong(stock.get("id"));
			} catch (Exception e) {
				return;
			}
			
		}
		Product temp = this.productMap.get(id);
		if(temp == null) {
			Long orderId = null;
			if(!stock.containsKey("orderid") ) {
				return;
			}
			try {
				orderId = Long.parseLong(stock.get("orderid"));
			} catch (Exception e) {
				return;
			}
			temp = new Product(id, orderId);
			this.productMap.put(temp.getId(), temp);
		}
		
		if(stock.containsKey("isfinished")) {
			Boolean isFinished;
			try {
				isFinished = Boolean.parseBoolean(stock.get("isfinished"));
			} catch (Exception e) {
				return;
			}
			temp.setFinished(isFinished);
		}
	}

	/**
	 * returns a complete collection of encoded Products from the storage
	 */
	@Override
	public Collection<Map<String, String>> listStock() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for(Product p :this.productMap.values()) {
			Map<String,String> rMap = new HashMap<String, String>();
			rMap.put("id", ""+p.getId());
			rMap.put("orderid", ""+p.getOrderid());
			rMap.put("isfinished", ""+p.isFinished());
			result.add(rMap);
		}
		return result;
	}

	@Override
	public void removeStock(long stockId) {
		this.productMap.remove(stockId);
	}
    
    

}
