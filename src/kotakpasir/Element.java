abstract class Element{
	private int absis;
	private int ordinat;
	private static double densitas;
	private static String nama;
	public int getAbsis(){
		return absis;
	}
	public int getOrdinat(){
		return ordinat;
	}
	public void setAbsis(int _absis){
		absis = _absis;
	}
	public void setOrdinat(int _ordinat){
		ordinat = _ordinat;
	}
	public double getDensitas(){
		return densitas;	
	}
	public String getNama(){
		return nama;
	}
	public void moveUp(){
		ordinat--;
	}
	public void moveDown(){
		ordinat++;
	}
	public void moveLeft(){
		absis--;
	}
	public void moveRight(){
		absis++;
	}
	public void moveUpRight(){
		moveUp();
		moveRight();
	}
	public void moveUpLeft(){
		moveUp();
		moveLeft();
	}
	public void moveDownRight(){
		moveDown();
		moveRight();
	}
	public void moveDownLeft(){
		moveDown();
		moveLeft();
	}
	abstract void life(String[][] a);
	abstract void meetOtherElement(Element OtherElement);
	public boolean isSameElement(Element OtherElement){
		return nama == OtherElement.getNama();
	}
}
