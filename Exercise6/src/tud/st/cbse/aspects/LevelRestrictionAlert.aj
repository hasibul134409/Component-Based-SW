package tud.st.cbse.aspects;

import tud.st.cbse.task6.building.Level;
import tud.st.cbse.task6.building.ComplexHousePart;
import tud.st.cbse.task6.building.HousePart;

public aspect LevelRestrictionAlert {
    pointcut addLevel(Level l, HousePart h) : call(* ComplexHousePart.addPart(..)) && target(l) && args(h);
    
	void around(Level l, HousePart h) : addLevel(l, h) {
		if(h instanceof Level) {
			System.out.println("Not possible");
		}
		else {
			proceed(l,h);
		}
	}

}
