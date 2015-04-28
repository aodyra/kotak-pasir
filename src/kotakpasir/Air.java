package kotakpasir;

import java.util.Random;
import java.util.Vector;

class Air extends Element{
	private static final double densitas = 1.0;
	private static final String nama = "Air";
	public Air(int _absis, int _ordinat, int _temperatur){
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
			a[getAbsis()][getOrdinat()] = "Air";
		}else if (a[getAbsis()][getOrdinat() + 1].compareTo("Tanah") == 0){
			Element E1 = new Tanah(0,0,getTemperatur());
			int i;
			for (i = 0 ; i < DE.size(); i++){
				E1 = DE.get(i);
				if ((E1.getAbsis() == getAbsis()) && (E1.getOrdinat() == getOrdinat() + 1))
					break;
			}
			DE.remove(i);
			a[getAbsis()][getOrdinat()] = "Zonk";
			moveDown();
			a[getAbsis()][getOrdinat()] = "Lumpur";
			Element E2 = new Lumpur(getAbsis(),getOrdinat(),getTemperatur());
			DE.addElement(E2);
			DE.removeElementAt(DE.indexOf(this));
		}else if ((a[getAbsis() + 1][getOrdinat() + 1].compareTo("Zonk") == 0) || (a[getAbsis() - 1][getOrdinat() + 1].compareTo("Zonk") == 0)){
			Random rand = new Random();
			int  n = rand.nextInt(2) + 1;
			if (n == 1){
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownLeft();
				a[getAbsis()][getOrdinat()] = "Air";
			}else{
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownRight();
				a[getAbsis()][getOrdinat()] = "Air";
			}
		}else if ((a[getAbsis() + 1][getOrdinat()].compareTo("Zonk") == 0) || (a[getAbsis() - 1][getOrdinat()].compareTo("Zonk") == 0)){
			Random rand = new Random();
			int  n = rand.nextInt(2) + 1;
			if (n == 1){
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveLeft();
				a[getAbsis()][getOrdinat()] = "Air";
			}else{
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveRight();
				a[getAbsis()][getOrdinat()] = "Air";
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
