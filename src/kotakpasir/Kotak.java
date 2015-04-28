package kotakpasir;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aodyra
 */
public class Kotak {
    public int suhu;
    public Vector<String> history;
    public Vector<Element> lifeelement;
    public Vector<String> elementTersedia;
    public String[][] World;
    public String currentElement;
    public boolean jalan;
    
    public Kotak(){
        suhu = 37;
        lifeelement = new Vector(15000);
        elementTersedia = new Vector(100);
        elementTersedia.addElement("Air");
        elementTersedia.addElement("Tanah");
        elementTersedia.addElement("Lumpur");
        World = new String[152][102];
        for (int i = 0; i <= 151; i++){
            for (int j = 0; j <= 101; j++){
                if ((i == 0 || i == 151) && (j == 0 || j == 101)){
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
        while (jalan){
            for(int i = 0; i < lifeelement.size();i++){
               El = lifeelement.get(i);
                El.life(World, lifeelement, suhu);
            }
        }
    }
    public void insert(int _absis,int _ordinat) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String E = "kotakpasir."+currentElement;
        if (elementTersedia.contains(E)){
            Element El = (Element) Class.forName(E).getConstructor(int.class,int.class,int.class).newInstance(_absis,_ordinat,suhu);
            lifeelement.addElement(El);
            history.addElement("Menambah "+El.getNama()+" di ("+_absis+","+_ordinat+")");
            World[_absis][_ordinat] = "E";
        }
    }
    public void gantiSuhu(int _suhu){
        suhu = _suhu;
        history.addElement("Merubah suhu menjadi "+suhu);
    }
    public void playpause(int x){
        if (x == 0){
            jalan = true;
        }else{
            jalan = false;
        }
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
        elementTersedia.addElement(_currentElement);
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
        for (i = 0; i < lifeelement.size(); i++){
            El = elementTersedia.get(i);
            if (El.compareTo(_El) == 0){
				delete(El.getAbsis(),El.getOrdinat());
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
