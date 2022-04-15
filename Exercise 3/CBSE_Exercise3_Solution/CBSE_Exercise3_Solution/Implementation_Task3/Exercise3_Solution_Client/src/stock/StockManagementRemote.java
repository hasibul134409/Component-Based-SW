package stock;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Remote;

/**
 * Copy of the remote interface of the StockManagement bean.
 * Needed for the client to know and use the bean methods.
 *
 */
@Remote
public interface StockManagementRemote {

	public Map<String,String> findStock( long stockId);
	
	public void addOrUpdate( Map<String, String> stock);
	
	public Collection<Map<String,String>> listStock();
	
	public void removeStock(long stockId);
	
}
