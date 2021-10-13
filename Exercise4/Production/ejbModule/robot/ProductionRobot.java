package robot;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ProductionRobot
 */
@Stateless
@LocalBean
public class ProductionRobot extends Robot implements ProductionRobotRemote, ProductionRobotLocal {

    /**
     * Default constructor. 
     */
    public ProductionRobot() {
    	this.robotName = "A";
    }

	@Override
	public void moveProduct(String destination) {
		System.out.println("Move product from " + this.robotName + "To " + destination);
	}
}
