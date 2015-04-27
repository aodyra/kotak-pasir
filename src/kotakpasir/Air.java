import java.util.Random;

class Air extends Element{
	private static final double densitas = 1.0;
	private static final String nama = "Air";
	public Air(int _absis, int _ordinat){
		setAbsis(_absis);
		setOrdinat(_ordinat);
	}
	public void life(String[][] a){
		if (a[getAbsis()][getOrdinat() + 1].compareTo("Zonk") == 0){
			a[getAbsis()][getOrdinat()] = "Zonk";
			moveDown();
			a[getAbsis()][getOrdinat()] = "Air";
		}else if (a[getAbsis()][getOrdinat() + 1].compareTo("Tanah") == 0){
			a[getAbsis()][getOrdinat()] = "Zonk";
			moveDown();
			a[getAbsis()][getOrdinat()] = "Lumpur";
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
	}
	public void meetOtherElement(Element OtherElement){}
}
