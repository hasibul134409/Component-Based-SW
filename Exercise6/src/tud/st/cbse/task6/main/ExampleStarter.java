package tud.st.cbse.task6.main;

import tud.st.cbse.task6.building.*;
import tud.st.cbse.task6.stakeholder.Person;

public class ExampleStarter {

	private House house;
	private Person owner = new Person("Christian Steel");
	private Person otherPerson = new Person("Emma Steel");
	private Room bathRoom;
	
	public static void main(String[] args){
		ExampleStarter starter = new ExampleStarter();
		starter.run();
	}
	
	public void run(){
		House h1 = buildSimpleHouse();
		h1.printStructure();
		h1.visit(owner);
		//Maybe the bathRoom should be cleaned
		//bathRoom.setClean(true);
		h1.visit(otherPerson);
	}
	
	private House buildSimpleHouse(){
		House house = new House("Street 1", owner);
		Level l0 = new Level(0);
		Level l1 = new Level(1);
		
		Room livingRoom = new Room("living room");
		Room sleepingRoom = new Room("sleeping room");
		sleepingRoom.setClean(false);		
		Room privateRoom = new Room("private room");
		Room bathRoom = new Bathroom();
		this.bathRoom=bathRoom;
		
		Hallway hallway = new Hallway();
		hallway.addPart(sleepingRoom);
		hallway.addPart(bathRoom);
		hallway.addPart(privateRoom);
		
		l1.addPart(hallway);
		l0.addPart(livingRoom);
		//Add inception room
		l0.addPart(l1);
		
		house.addPart(l0);
		house.addPart(l1);
		
		return house;
	}
	
}
