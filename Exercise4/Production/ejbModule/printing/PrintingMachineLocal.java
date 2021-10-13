package printing;

import javax.ejb.Local;

@Local
public interface PrintingMachineLocal {
	 void startPrinting();
	 void finishPrinting();
	 void moveProductToRobot(String robotName);
}
