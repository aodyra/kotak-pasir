package kotakpasir;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

public class Batu extends Element{
	private static final double densitas = 2.0;
	private static final String nama = "Tanah";
        private static final Color warna = new Color(218,182,97);
        private int penghancuran;
	public Batu(int _absis, int _ordinat, int _temperatur){
		setAbsis(_absis);
		setOrdinat(_ordinat);
		setTemperatur(_temperatur);
	}
	public String getNama(){
		return nama;
	}
        public Color getWarna(){
            return warna;
        }
        public double getDensitas(){
            return densitas;
        }
	public void life(String[][] a, Vector<Element> DE, int EnvTemp){
		if (a[getAbsis()][getOrdinat() + 1].compareTo("Zonk") == 0){
			a[getAbsis()][getOrdinat()] = "Zonk";
			moveDown();
			a[getAbsis()][getOrdinat()] = "Batu";
		}else if ((a[getAbsis() + 1][getOrdinat() + 1].compareTo("Zonk") == 0) || (a[getAbsis() - 1][getOrdinat() + 1].compareTo("Zonk") == 0)){
			Random rand = new Random();
			int  n = rand.nextInt(2) + 1;
                        if ((a[getAbsis() + 1][getOrdinat() + 1].compareTo("Zonk") == 0) && (a[getAbsis() - 1][getOrdinat() + 1].compareTo("Zonk") != 0))
                            n = 2;
                        if ((a[getAbsis() + 1][getOrdinat() + 1].compareTo("Zonk") != 0) && (a[getAbsis() - 1][getOrdinat() + 1].compareTo("Zonk") == 0))
                            n = 1;
			if (n == 1){
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownLeft();
				a[getAbsis()][getOrdinat()] = "Batu";
			}else{
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownRight();
				a[getAbsis()][getOrdinat()] = "Batu";
			}
		}
		if (EnvTemp < getTemperatur()){
			mendingin();
		}
		if (EnvTemp > getTemperatur()){
			memanas();
		}
	}
}
