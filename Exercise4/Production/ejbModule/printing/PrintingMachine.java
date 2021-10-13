package printing;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import machine.Machine;
import machine.RobotArms;

/**
 * Session Bean implementation class PrintingMachine
 */
@Stateless
@LocalBean
public class PrintingMachine extends Machine implements PrintingMachineRemote, PrintingMachineLocal {

    /**
     * Default constructor. 
     */
    public PrintingMachine() {
    	this.setRobotArm(new RobotArms());
    }

	@Override
	public void startPrinting() {
		System.out.println("Priting has been started");
	}

	@Override
	public void finishPrinting() {
		System.out.println("Priting has been finished");		
	}

	@Override
	public void moveProductToRobot(String robotName) {
		this.robotArm.moveProductToRobot(robotName);
	}

}
