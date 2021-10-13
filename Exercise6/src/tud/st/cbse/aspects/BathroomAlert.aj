package tud.st.cbse.aspects;

import tud.st.cbse.task6.building.Bathroom;
import tud.st.cbse.task6.building.HousePart;
import tud.st.cbse.task6.stakeholder.Person;

public aspect BathroomAlert {
	pointcut enterBathroom(Bathroom b, Person p) : call(* HousePart.enter(..)) && target(b) && args(p);
	
	void around(Bathroom b, Person p) : enterBathroom(b,p) {
		if(!b.isClean()) {
			System.out.println("Warning!! Bathroom not clean. Dont enter");
		}
		else
			proceed(b,p);
	}
	
}
