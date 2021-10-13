package facade;

import javax.ejb.Remote;

@Remote
public interface PrintingShopProductionFacadeRemote {
	void startProduction();
}
