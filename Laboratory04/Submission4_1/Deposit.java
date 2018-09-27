package Laboratory04.Submission4_1;

public class Deposit extends Transaction {
	
	public Deposit() { this(0.0); }
	public Deposit(double amount) { super(amount); }

	@Override
	public boolean apply(TransactionalBankAccount account) {
		try { 
			account.deposit(this.getAmount());
			account.setMostRecentTransaction(this);
		} catch (Exception e) { return false; }
		return true;
	}

	/**
	 * Generates textual information about transaction type.
	 * 
	 * @return "DEPOSIT"
	 */
	@Override
	protected String getTransactionType() { return "DEPOSIT"; }
}
