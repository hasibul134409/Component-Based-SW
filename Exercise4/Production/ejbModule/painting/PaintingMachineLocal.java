package painting;

import javax.ejb.Local;

@Local
public interface PaintingMachineLocal {
	void startPainting();
	void finishPainting();
	void moveProductToRobot(String robotName);
}
