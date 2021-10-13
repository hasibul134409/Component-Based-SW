package tud.st.cbse.aspects;

import tud.st.cbse.task6.building.Bathroom;
import tud.st.cbse.task6.stakeholder.Person;
import tud.st.cbse.task6.building.HousePart;

public aspect BathroomMarker {
    pointcut enterBathroom(Bathroom b, Person p) : call(* HousePart.enter(..)) && target(b) && args(p);
	
	//void around(Bathroom b, Person p) : enterBathroom(b,p) {
	//	proceed(b,p);
	//}
	
	after(Bathroom b, Person p) : enterBathroom(b, p) {
		b.setClean(false);
	}
}
