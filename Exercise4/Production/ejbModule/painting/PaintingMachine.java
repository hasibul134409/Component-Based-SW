package painting;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import machine.Machine;
import machine.RobotArms;

/**
 * Session Bean implementation class PaintingMachine
 */
@Stateless
@LocalBean
public class PaintingMachine extends Machine implements PaintingMachineRemote, PaintingMachineLocal {

    /**
     * Default constructor. 
     */
    public PaintingMachine() {
    	this.setRobotArm(new RobotArms());
    }

	@Override
	public void startPainting() {
		System.out.println("Start Painting");
	}

	@Override
	public void finishPainting() {
		System.out.println("Finish Painting");		
	}

	@Override
	public void moveProductToRobot(String robotName) {
		this.robotArm.moveProductToRobot(robotName);
	}

}
