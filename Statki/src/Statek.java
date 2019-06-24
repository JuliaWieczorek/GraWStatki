import java.util.ArrayList;
import java.util.List;

public class Statek{
	
	int wielkosc;
	static boolean zatopiony;
	private	List<Pole> maszty = new ArrayList<Pole>();

	public Statek(String size, String lista) {
		this.wielkosc = Integer.parseInt(size);	
		for (int i = 0; i < wielkosc; i++) {
			int row = Character.getNumericValue(lista.charAt(0+ 3*i));
			int col = Character.getNumericValue(lista.charAt(1+ 3*i));
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
		}
	}

