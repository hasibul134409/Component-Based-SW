package painting;

import javax.ejb.Remote;

@Remote
public interface PaintingMachineRemote {
	void startPainting();
	void finishPainting();
	void moveProductToRobot(String robotName);
}
