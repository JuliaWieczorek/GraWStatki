
public class Maszt extends Pole{
	
	//czy Maszt dziedziczy po Polu??
	
	private static final long serialVersionUID = 1L;
	private boolean stan;

	public Maszt(int kogo, int row, int col, String value) {
		super(kogo, row, col, value);
		
	}

	public boolean isStan() {
		return stan;
	}

	public void setStan(boolean stan) {
		System.out.println("Trafiony");
		this.stan = stan;
	}

}
