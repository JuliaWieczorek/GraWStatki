import javax.swing.JButton;

public class Statek {
	
	int wielkosc;
	JButton polozenie;
	
	public Statek() {
		
	}

	public int getWielkosc() {
		return wielkosc;
	}

	public void setWielkosc(int wielkosc) {
		this.wielkosc = wielkosc;
	}
	
	public JButton getPolozenie() {
		return polozenie;
	}

	public void setPolozenie(JButton pole) {
		this.polozenie = pole;
	}

	@Override
	public String toString() {
		return "Statek [wielkosc=" + wielkosc + ", polozenie=" + polozenie + "]";
	}	
	
}
