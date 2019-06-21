import java.util.ArrayList;
import java.util.List;

public class Statek{
	
	int wielkosc;
	static List<Pole> maszty = new ArrayList<Pole>();
	
	public Statek(String size, String lista) {
		//System.out.println(lista.charAt(2+1));
		this.wielkosc = Integer.parseInt(size);	
		for (int i = 0; i < wielkosc; i++) {
			//System.out.print(lista);
			int row = Character.getNumericValue(lista.charAt(0+ 3*i));
			int col = Character.getNumericValue(lista.charAt(1+ 3*i));
			//System.out.println(row+" "+col);
			maszty.add(Plansza.pola[row][col]);
		}
	}


	public int getWielkosc() {
		return wielkosc;
	}

	public void setWielkosc(int wielkosc) {
		this.wielkosc = wielkosc;
	}
	
	public static void czyZatopiony() {
		// sumowac true w stanach masztow jesli rowne wielkosci statku to zatopiony
		System.out.print(maszty.get(0).isStan());
	}
	
	
	
	@Override
	public String toString() {
		return "Statek [wielkosc=" + wielkosc + "]";
	}	
	
}
