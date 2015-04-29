package kotakpasir;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static kotakpasir.Kotak.World;
import static kotakpasir.Kotak.currentElement;
import static kotakpasir.Kotak.history;
import static kotakpasir.Kotak.lifeelement;
import static kotakpasir.Kotak.suhu;

public class Air extends Element{
	private static final double densitas = 1.0;
	private static final String nama = "Air";
        private static final Color warna = new Color(73,145,203);
	public Air(int _absis, int _ordinat, int _temperatur){
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
                double densitasatas = 0;
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
		}else if (a[getAbsis()][getOrdinat() - 1].compareTo("Zonk") != 0 && a[getAbsis()][getOrdinat() - 1].compareTo("Frame") != 0){
                    String E = "kotakpasir."+a[getAbsis()][getOrdinat() - 1];
                    Element El1 = null;
                    try {
                        El1 = (Element) Class.forName(E).getConstructor(int.class,int.class,int.class).newInstance(0,0,suhu);
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (El1 != null){
                        densitasatas = El1.getDensitas();
                        if (densitasatas > densitas){
                            for (int i = 0; i < DE.size(); i++){
                                El1 = DE.get(i);
                                if (El1.getAbsis() == getAbsis() && El1.getOrdinat() == (getOrdinat() - 1)){
                                    break;
                                }
                            }
                            a[getAbsis()][getOrdinat()] = El1.getNama();
                            a[getAbsis()][getOrdinat() - 1] = getNama();
                            El1.moveDown();
                            moveUp();
                        }
                    }
                }else if (a[getAbsis() - 1][getOrdinat() - 1].compareTo("Zonk") != 0 && a[getAbsis() - 1][getOrdinat() - 1].compareTo("Frame") != 0){
                    String E = "kotakpasir."+a[getAbsis() - 1][getOrdinat() - 1];
                    Element El1 = null;
                    try {
                        El1 = (Element) Class.forName(E).getConstructor(int.class,int.class,int.class).newInstance(0,0,suhu);
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (El1 != null){
                        densitasatas = El1.getDensitas();
                        if (densitasatas > densitas){
                            for (int i = 0; i < DE.size(); i++){
                                El1 = DE.get(i);
                                if ((El1.getAbsis() == getAbsis() - 1) && El1.getOrdinat() == (getOrdinat() - 1)){
                                    break;
                                }
                            }
                            a[getAbsis()][getOrdinat()] = El1.getNama();
                            a[getAbsis() - 1][getOrdinat() - 1] = getNama();
                            El1.moveDown();
                            moveUpLeft();
                        }
                    }
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
				a[getAbsis()][getOrdinat()] = "Air";
			}else{
				a[getAbsis()][getOrdinat()] = "Zonk";
				moveDownRight();
				a[getAbsis()][getOrdinat()] = "Air";
			}
		}else if ((a[getAbsis() + 1][getOrdinat()].compareTo("Zonk") == 0) || (a[getAbsis() - 1][getOrdinat()].compareTo("Zonk") == 0)){
			Random rand = new Random();
			int  n = rand.nextInt(2) + 1;
                        if ((a[getAbsis() + 1][getOrdinat()].compareTo("Zonk") == 0) && (a[getAbsis() - 1][getOrdinat()].compareTo("Zonk") != 0))
                            n = 2;
                        if ((a[getAbsis() + 1][getOrdinat()].compareTo("Zonk") != 0) && (a[getAbsis() - 1][getOrdinat()].compareTo("Zonk") == 0))
                            n = 1;
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
}
