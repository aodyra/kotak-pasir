package kotakpasir;

import java.util.Vector;
import java.awt.Color;

abstract class Element{
	private int absis;
	private int ordinat;
	private int temperatur;
	private static double densitas;
	private static String nama;
        private static Color warna;
	public int getAbsis(){
		return absis;
	}
	public int getOrdinat(){
		return ordinat;
	}
	public int getTemperatur(){
		return temperatur;
	}
	public void setAbsis(int _absis){
		absis = _absis;
	}
	public void setOrdinat(int _ordinat){
		ordinat = _ordinat;
	}
	public void setTemperatur(int _temperatur){
		temperatur = _temperatur;
	}
	public void mendingin(){
		temperatur--;
	}
	public void memanas(){
		temperatur++;
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
	abstract void life(String[][] a, Vector<Element> DE, int EnvTemp);
	abstract void meetOtherElement(Element OtherElement);
	public boolean isSameElement(Element OtherElement){
		return nama == OtherElement.getNama();
	}
        public Color getWarna(){
            return warna;
        }
}
