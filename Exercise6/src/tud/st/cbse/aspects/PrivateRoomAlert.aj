package tud.st.cbse.aspects;

import tud.st.cbse.task6.building.Room;
import tud.st.cbse.task6.stakeholder.Person;
import tud.st.cbse.task6.building.HousePart;
import tud.st.cbse.task6.building.House;

public aspect PrivateRoomAlert {
	pointcut enterRoom(Room r, Person p) : call(* HousePart.enter(..)) && target(r) && args(p);
	
	void around(Room r, Person p) : enterRoom(r,p) {
		if(r.getRoomName().equals("private room")) {
			House house = (House) r.getSuperPart().getSuperPart().getSuperPart();
			if(house.getOwner().getName().equals(p.getName())) {
				proceed(r ,p);
			}
			else {
				System.out.println("Warning!! You are not allowed");
			}
		}
	}
}
