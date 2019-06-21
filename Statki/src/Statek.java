import java.util.ArrayList;
import java.util.List;

public class Statek{
	
	int wielkosc;
	private List<Pole> maszty = new ArrayList<Pole>();
	
	public Statek(String size, String lista) {
		//System.out.println(lista.charAt(2+1));
		this.wielkosc = Integer.parseInt(size);	
		for (int i = 0; i < wielkosc; i++) {
			int row = Character.getNumericValue(lista.charAt(0+ 3*i));
			int col = Character.getNumericValue(lista.charAt(1+ 3*i));
			//System.out.println(row+" "+col);
			maszty.add(Plansza.pola[row][col]);
			Plansza.pola[row][col].setStatek(this);
		}
	}


	public int getWielkosc() {
		return wielkosc;
	}

	public void setWielkosc(int wielkosc) {
		this.wielkosc = wielkosc;
	}
	
	public void czyZatopiony() {
		
		int ile_trafionych = 0;
		for (int i=0; i<this.wielkosc ; i++) {
			if(this.maszty.get(i).isStan()==true) {ile_trafionych+=1;}
		}
		if (ile_trafionych == this.wielkosc) {
			System.out.println("Zatopiony: "+this.wielkosc+"-masztowiec");
		}
	}
		
}
