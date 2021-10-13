package tud.st.cbse.task6.building;

public class Level extends ComplexHousePart {

	private int number;
	
	public Level(int number){
		this.number = number;
	}
	
	@Override
	public void printStructure() {
		System.out.println("Level " + number);
		super.printStructure();
	}
	
}
