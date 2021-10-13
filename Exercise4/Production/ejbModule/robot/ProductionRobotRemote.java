package robot;

import javax.ejb.Remote;

@Remote
public interface ProductionRobotRemote {
	public void moveProduct(String destination);

}
