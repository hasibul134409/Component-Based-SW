package tud.st.cbse.task6.building;

public class Flat extends ComplexHousePart {

	private String flatNumber;
	
	public Flat(String flatNumber){
		this.flatNumber = flatNumber;
	}
	
	@Override
	public void printStructure() {
		System.out.print("Flat " + flatNumber);
		super.printStructure();
	}
	
}
