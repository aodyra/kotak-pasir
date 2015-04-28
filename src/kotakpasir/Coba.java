import java.util.Vector;

class Coba{
	public static void main(String args[]){
		int temperatur = 37;
		Vector<Element> DE = new Vector(100);
		Element El = new Tanah(0,4,temperatur);
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
		String[][] Kotak = new String[4][5];
		Kotak[0][0] = "Zonk";
		Kotak[0][1] = "Zonk";
		Kotak[0][2] = "Zonk";
		Kotak[0][3] = "Zonk";
		Kotak[0][4] = "Tanah";
		Kotak[1][0] = "Zonk";
		Kotak[1][1] = "Air";
		Kotak[1][2] = "Zonk";
		Kotak[1][3] = "Zonk";
		Kotak[1][4] = "Tanah";
		Kotak[2][0] = "Zonk";
		Kotak[2][1] = "Zonk";
		Kotak[2][2] = "Zonk";
		Kotak[2][3] = "Zonk";
		Kotak[2][4] = "Tanah";
		Kotak[3][0] = "Zonk";
		Kotak[3][1] = "Zonk";
		Kotak[3][2] = "Zonk";
		Kotak[3][3] = "Zonk";
		Kotak[3][4] = "Tanah";
		for (i = 0 ; i < DE.size(); i++){
			El = DE.get(i);
			System.out.println(El.getNama()+">"+El.getAbsis()+","+El.getOrdinat()+","+El.getTemperatur());
		}
		System.out.println();
		El = DE.get(4);
		El.life(Kotak,DE,50);
		for (i = 0 ; i < DE.size(); i++){
			El = DE.get(i);
			System.out.println(El.getNama()+">"+El.getAbsis()+","+El.getOrdinat()+","+El.getTemperatur());
		}
		System.out.println();
		El = DE.get(4);
		El.life(Kotak,DE,50);
		for (i = 0 ; i < DE.size(); i++){
			El = DE.get(i);
			System.out.println(El.getNama()+">"+El.getAbsis()+","+El.getOrdinat()+","+El.getTemperatur());
		}
		System.out.println();
		El = DE.get(4);
		El.life(Kotak,DE,50);
		for (i = 0 ; i < DE.size(); i++){
			El = DE.get(i);
			System.out.println(El.getNama()+">"+El.getAbsis()+","+El.getOrdinat()+","+El.getTemperatur());
		}
	}
}
