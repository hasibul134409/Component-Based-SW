package tud.st.cbse.aspects;

import tud.st.cbse.task6.main.*;

public aspect LoggingAspect {

	pointcut running(ExampleStarter s) : call(* run()) && target(s);
	
	before(ExampleStarter s) : running(s){
		System.out.println("Program Started");
	}	
	after(ExampleStarter s) : running(s){
		System.out.println("Program Stopped");
	}
}
