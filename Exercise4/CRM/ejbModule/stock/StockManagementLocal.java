package stock;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;

/**
 * Local Interface for the StockManagment Bean.
 * Can be used from beans in the same module/container.
 * Can have other/more methods/options then the remote one.
 */
@Local
public interface StockManagementLocal {
	
	public Map<String,String> findStock( long stockId);
	
	public void addOrUpdate( Map<String, String> stock);
	
	public Collection<Map<String,String>> listStock();
	
	public void removeStock(long stockId);

}
