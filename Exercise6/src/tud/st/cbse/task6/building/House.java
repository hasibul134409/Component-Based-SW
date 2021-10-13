package tud.st.cbse.task6.building;

import tud.st.cbse.task6.stakeholder.Person;

public class House extends ComplexHousePart {
	
	private String address;
	private Person owner;
	
	public House(String address, Person owner){
		this.address = address;
		this.owner = owner;
	}
	
	@Override
	public void printStructure() {
		System.out.println("House " + address);
		super.printStructure();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
}
