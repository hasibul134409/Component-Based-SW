package robot;

import javax.ejb.Local;

@Local
public interface ProductionRobotLocal {
	public void moveProduct(String destination);
}
