package stock;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Remote;

/**
 * Remote Interface for the OrderManagment Bean.
 * 
 * A copy of this interface must be provided to the client that want to use the bean.
 * The copy can be provided as a .java, .class, or in a .jar file. 
 * The provided file must be included in the client project (with same package structure).
 * 
 * Please note that this Interface only sends out data coded into Strings.
 * This means that no additional interface or class definition needs to be provided to the user to use the interface.
 * But the user needs to know the en- and decoding of the String data.
 * 
 */
@Remote
public interface StockManagementRemote {

	public Map<String,String> findStock( long stockId);
	
	public void addOrUpdate( Map<String, String> stock);
	
	public Collection<Map<String,String>> listStock();
	
	public void removeStock(long stockId);
	
}
