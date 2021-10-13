package tud.st.cbse.aspects;

import tud.st.cbse.task6.building.Room;
import tud.st.cbse.task6.stakeholder.Person;
import tud.st.cbse.task6.building.Bathroom;
import tud.st.cbse.task6.building.HousePart;

public aspect RoomAlert {
	pointcut enterRoom(Room r, Person p) : call(* HousePart.enter(..)) && target(r) && args(p);
	
	after(Room r, Person p) : enterRoom(r, p) {
		if(!r.isClean()) {
			System.out.println(" Warning!!! Room is not clean");
		}
	}
}
