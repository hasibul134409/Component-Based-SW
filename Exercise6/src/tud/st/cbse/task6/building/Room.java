package tud.st.cbse.task6.building;

import tud.st.cbse.task6.stakeholder.Person;

public class Room extends HousePart {
	
	private String roomName;
	
	public Room(String roomName){
		this.roomName = roomName;
	}

	@Override
	public void printStructure() {
		System.out.print("Room \"" + roomName + "\"");
	}

	@Override
	public void visit(Person p) {
		enter(p);
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	

}
