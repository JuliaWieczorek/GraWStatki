
public class Maszt extends Pole{
	
	private static final long serialVersionUID = 1L;
	private boolean stan;
	private Statek statek;

	public Maszt(int kogo, int row, int col, String value, Gracz gracz) {
		super(kogo, row, col, value,gracz);
		
	}

	public boolean isStan() {
		return stan;
	}

	public void setStan(boolean stan) {
		//System.out.println("Trafiony");
		this.stan = stan;
	}

	public Statek getStatek() {
		return statek;
	}

	public void setStatek(Statek statek) {
		this.statek = statek;
	}

}
