package facade;

import javax.ejb.Local;

@Local
public interface PrintingShopProductionFacadeLocal {
	void startProduction();
}
