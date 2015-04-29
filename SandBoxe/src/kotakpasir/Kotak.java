package kotakpasir;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aodyra
 */
public final class Kotak {
    public static int suhu;
    public static Vector<String> history;
    public static Vector<Element> lifeelement;
    public static Vector<String> elementTersedia;
    public static String[][] World;
    public static String currentElement;
    
    public Kotak(){
        suhu = 37;
        history = new Vector(30000);
        lifeelement = new Vector(15000);
        elementTersedia = new Vector(100);
        elementTersedia.addElement("Air");
        elementTersedia.addElement("Tanah");
        elementTersedia.addElement("Lumpur");
        World = new String[152][102];
        for (int i = 0; i <= 151; i++){
            for (int j = 0; j <= 101; j++){
                if ((i == 0 || i == 151) || (j == 0 || j == 101)){
                    World[i][j] = "Frame";
                }else{
                    World[i][j] = "Zonk";
                }
            }
        }
        currentElement = "Tanah";
    }
    public void run(){
        Element El = new Tanah(0,0,suhu);
        for(int i = 0; i < lifeelement.size();i++){
            El = lifeelement.get(i);
            El.life(World, lifeelement, suhu);
        }
    }
    public void newWorld(){
        while(!(lifeelement.isEmpty())){
            Element El = lifeelement.get(0);
            World[El.getAbsis()][El.getOrdinat()] = "Zonk";
            lifeelement.remove(0);
        }
    }
    public void insert(int _absis,int _ordinat){
        String E = "kotakpasir."+currentElement;
        Element El = null;
        try {
            El = (Element) Class.forName(E).getConstructor(int.class,int.class,int.class).newInstance(_absis,_ordinat,suhu);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (El != null){
            lifeelement.addElement(El);
            history.addElement("Menambah "+currentElement+" di ("+_absis+","+_ordinat+")");
            World[_absis][_ordinat] = currentElement;
        }
    }
    public int getSuhu(){
        return suhu;
    }
    public void gantiSuhu(int _suhu){
        suhu = _suhu;
        history.addElement("Merubah suhu menjadi "+suhu);
    }
    public void delete(int _absis,int _ordinat){
        Element El = new Tanah(0,0,suhu);
        int i;
        for (i = 0; i < lifeelement.size(); i++){
            El = lifeelement.get(i);
            if ((El.getAbsis() == _absis) && (El.getOrdinat() == _ordinat))
                break;
        }
        if (i != lifeelement.size())
            lifeelement.remove(i);
        World[_absis][_ordinat] = "Zonk";
        history.addElement("Menghapus "+El.getNama()+" di ("+_absis+","+_ordinat+")");
    }
    public void setElement(String _currentElement){
		currentElement = _currentElement;
    }
    public void addElement(String _El){
        elementTersedia.addElement(_El);
        history.addElement("Menambah element "+_El);
    }
    public void removeElement(String _El){
		String El;
		int i;
        for (i = 0; i < elementTersedia.size(); i++){
            El = elementTersedia.get(i);
            if (El.compareTo(_El) == 0)
                break;
        }
        if (i != elementTersedia.size())
			elementTersedia.remove(i);
        history.addElement("Menghapus element "+_El);
        Element El1 = new Tanah(0,0,suhu);
        for (i = 0; i < lifeelement.size(); i++){
            El1 = lifeelement.get(i);
            if (El1.getNama().compareTo(_El) == 0){
				delete(El1.getAbsis(),El1.getOrdinat());
			}
		}
	}
    public void setWorld(String[][] _World){
        for (int i = 0; i <= 151; i++){
            for (int j = 0; j <= 101; j++){
				World[i][j] = _World[i][j];
            }
        }
    }
    public String[][] getWorld(){
		return World;
	}
	public void addHistory(String _history){
		history.addElement(_history);
	}
	public Vector<String> getHistory(){
		return history;
	}
}
