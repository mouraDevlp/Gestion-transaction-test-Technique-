import java.util.Comparator;

public class compareTransactionByQuantityDesc implements Comparator<Transaction>{

	@Override
	public int compare(Transaction o1, Transaction o2) {
		// TODO Auto-generated method stub
		return -(o1.getQuantite() - o2.getQuantite());
	}

}
