package Laboratory04.Submission4_1;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

/**
 * represents a single transaction
 * on a single bank account
 * @see TransactionalBankAccount
 */
public abstract class Transaction {

	private Date date;
	private double amount;

	/**
	 * A default no-arg constructor. 
	 * Sets <code>amount</code> to 0.0 
	 * and <code>date</code> to the current date.
	 */
	public Transaction() { this(0.0); }
	
	/**
	 * This constructor allows to set <code>amount</code>
	 * to a custom value.
	 * 
	 * @param amount
	 */
	public Transaction(double amount) { 
		this.setAmount(amount);
		this.setDate(Calendar.getInstance().getTime());
		}

	public Date getDate() { return this.date; }
	public double getAmount() { return this.amount; }
	
	public void setDate(Date date) { this.date = date; }
	public void setAmount(double amount) { this.amount = amount; }
	
	/**
	 * Returns a textual representation of transaction including the date, 
	 * transaction type, currency and amount.
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%s %s %s %.2f", this.getFormattedDate(), this.getTransactionType(),
				Currency.getInstance("GBP").getSymbol(), this.amount);
	}
	
	/**
	 * Utility method - returns object's date in ISO format and time in format HH:MM.
	 * 
	 * @return textual representation of date and time.
	 */
	private String getFormattedDate() { return String.format("\n%tF %<tR", this.getDate()); }

	/**
	 * 
	 * @return "DEPOSIT" or "WITHDRAWAL".
	 */
	protected abstract String getTransactionType();

	/**
	 * Applies valid transaction to <code>account</code>.
	 * 
	 * @param account
	 * @return <code>true</code> if transaction succesful, <code>false</code>
	 * otherwise. 
	 */
	public abstract boolean apply(TransactionalBankAccount account);
}