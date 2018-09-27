package Laboratory04.Submission4_1;

public class Withdrawal extends Transaction {
	
	public Withdrawal() { this(0.0); }
	public Withdrawal(double amount) { super(amount); }

	@Override
	public boolean apply(TransactionalBankAccount account) {
		try {
			if(account.withdraw(this.getAmount())) account.setMostRecentTransaction(this);
		} catch (Exception e) { return false; }
		return this.equals(account.getMostRecentTransaction());
	}
	
	/**
	 * Generates textual information about transaction type.
	 * 
	 * @return "WITHDRAWAL"
	 */
	@Override
	protected String getTransactionType() { return "WITHDRAWAL"; }
}
