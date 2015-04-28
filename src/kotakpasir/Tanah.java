package kotakpasir;

import java.util.Random;
import java.util.Vector;

class Tanah extends Element{
	private static final double densitas = 1.0;
	private static final String nama = "Tanah";
	public Tanah(int _absis, int _ordinat, int _temperatur){
		setAbsis(_absis);
		setOrdinat(_ordinat);
		setTemperatur(_temperatur);
	}
	public String getNama(){
		return nama;
	}
	public void life(String[][] a, Vector<Element> DE, int EnvTemp){
		if (a[getAbsis()][getOrdinat() + 1].compareTo("Zonk") == 0){
			a[getAbsis()][getOrdinat()] = "Zonk";
			moveDown();
			a[getAbsis()][getOrdinat()] = "Tanah";
		}else if ((a[getAbsis() + 1][getOrdinat() + 1].compareTo("Zonk") == 0) || (a[getAbsis() - 1][getOrdinat() + 1].compareTo("Zonk") == 0)){
			Random rand = new Random();
			int  n = rand.nextInt(2) + 1;
			if (n == 1){
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownLeft();
				a[getAbsis()][getOrdinat()] = "Tanah";
			}else{
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownRight();
				a[getAbsis()][getOrdinat()] = "Tanah";
			}
		}
		if (EnvTemp < getTemperatur()){
			mendingin();
		}
		if (EnvTemp > getTemperatur()){
			memanas();
		}
	}
	public void meetOtherElement(Element OtherElement){}
}
