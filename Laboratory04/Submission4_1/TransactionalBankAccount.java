package Laboratory04.Submission4_1;

/**
 * subclass of @see BankAccount that
 * keeps track of the most recent 
 * @see Transaction on this account
 */
public class TransactionalBankAccount extends BankAccount {

	private Transaction mostRecentTransaction;

    public TransactionalBankAccount() { super(); }
    public TransactionalBankAccount(String holder, double limit) { super(holder, limit); }

	public Transaction getMostRecentTransaction() { return this.mostRecentTransaction; }
	public void setMostRecentTransaction(Transaction transaction) { this.mostRecentTransaction = transaction; }

	/**
	 * Generates textual account report including account and holder details plus
	 * details about last successful transaction.
	 * 
	 * @return account details in string format.
	 */
    @Override
    public String toString() { return super.toString() + "\n" + 
    (this.getMostRecentTransaction() == null ? "" : this.mostRecentTransaction.toString()); }
}