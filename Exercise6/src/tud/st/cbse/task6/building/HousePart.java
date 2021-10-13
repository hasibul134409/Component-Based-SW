package tud.st.cbse.task6.building;

import tud.st.cbse.task6.stakeholder.Person;

public abstract class HousePart {

	private boolean isClean;
	private HousePart superPart;
	
	public HousePart(){
		isClean = true;
	}
	
	public boolean isClean() {
		return isClean;
	}

	public void setClean(boolean isClean) {
		this.isClean = isClean;
	}
	
	public void enter(Person p){
		
	}

	public HousePart getSuperPart() {
		return superPart;
	}

	public void setSuperPart(HousePart superPart) {
		this.superPart = superPart;
	}

	public abstract void printStructure();
	public abstract void visit(Person p);
	
}
