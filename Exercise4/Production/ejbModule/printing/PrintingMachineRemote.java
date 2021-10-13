package printing;

import javax.ejb.Remote;

@Remote
public interface PrintingMachineRemote {
	 void startPrinting();
	 void finishPrinting();
	 void moveProductToRobot(String robotName);
}
