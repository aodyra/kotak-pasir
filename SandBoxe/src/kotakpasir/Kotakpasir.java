/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kotakpasir;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aodyra
 */
public class Kotakpasir {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // TODO code application logic here
        int temperatur = 37;
        Vector<Element> DE = new Vector(100);
        Element El = (Element) Class.forName("kotakpasir.Tanah").getConstructor(int.class,int.class,int.class).newInstance(1,1,temperatur);
        
        DE.addElement(El);
        El = new Tanah(1,4,temperatur);
        DE.addElement(El);
        El = new Tanah(2,4,temperatur);
        DE.addElement(El);
        El = new Tanah(3,4,temperatur);
        DE.addElement(El);
        El = new Air(1,1,temperatur);
        DE.addElement(El);
        int i;
        Kotak kotak = new Kotak();
        for (i = 0 ; i < 10; i++){
            kotak.run();
            El = kotak.lifeelement.get(0);
            System.out.println(El.getNama()+">"+El.getAbsis()+","+El.getOrdinat()+","+El.getTemperatur());
        }
    }*/
}
