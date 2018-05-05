import java.util.Comparator;

public class compareTransactionByChiffreAffaireDesc implements Comparator<Transaction> {

	@Override
	public int compare(Transaction o1, Transaction o2) {
		// TODO Auto-generated method stub
		int a = (int) o1.getPrix() * o1.getQuantite();
		int b = (int) o2.getPrix() * o2.getQuantite();
		return -(a - b) ;
	}
	
	
}
