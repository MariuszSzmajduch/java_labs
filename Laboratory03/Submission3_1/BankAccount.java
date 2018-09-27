package Laboratory03.Submission3_1;

import java.util.Currency;

/**
 * Assessed submission for JP2 lab 3
 * This class represents a simple bank account
 */
public class BankAccount {
    
    /**
     * each BankAccount instance should have
     * a unique account number
     */
	private static int currAccNo = 0;
	private static final String POUND = Currency.getInstance("GBP").getSymbol();
	
	private int accountNumber;
    private String accountHolder;
	private double currentBalance;	
	private double overdraftLimit;
	
    public int getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getCurrentBalance() { return currentBalance; }
    public double getOverdraftLimit() { return overdraftLimit; }
    
    public void setAccountHolder(String accountHolder) {
    	this.accountHolder = accountHolder; 
    }
    
    public boolean setOverdraftLimit(double overdraftLimit)  {
    	this.overdraftLimit = this.overdraftLimit > overdraftLimit? this.overdraftLimit : overdraftLimit;
    	return this.overdraftLimit == overdraftLimit;    		
    }

    /**
     * Assigns first available accountNumber, sets all values to 0.0
     * and accountHolder to an empty string.    
     */
    public BankAccount() { this("", 0.0); }
    
    /**
     * Sets accountHolder and overdraftLimit as specified, accountNumber to first available
     * and currentBalance equal to 0.
     * 
     * @param accountHolder
     * @param overdraftLimit
     */
    public BankAccount(String accountHolder, double overdraftLimit) {
    	this.accountHolder = accountHolder;
    	this.currentBalance = 0.0;
    	this.overdraftLimit = overdraftLimit;
    	this.accountNumber = currAccNo++;
    }    
    
    /**
     * Increases current balance by a deposit.
     * 
     * @param amount
     * @throws Exception, <code>amount</code> must be positive or 0.
     */
    public void deposit(double amount) throws Exception {
    	if (amount < 0) throw new Exception();
    	this.currentBalance += amount;
    }
    
   /**
    * Withdraws from an account on the condition that overdraft limit is not exceeded.
    * 
    * @param amount
    * @return true if the operation succeeds, false otherwise.
    * @throws Exception when <code>amount</code> is negative.
    */
    public boolean withdraw(double amount) throws Exception {
    	if (amount < 0) throw new Exception();
    	if(this.currentBalance - amount < -this.overdraftLimit) return false;
    	this.currentBalance -= amount;
    	return true;
    }
    
    /**
     * Creates a string containing account details.
     */
    @Override
    public String toString(){
    	return  String.format("%s %d %s%s%.2f %.2f", this.accountHolder, this.accountNumber,
    			this.currentBalance < 0 ? "-" : "", POUND, Math.abs(this.currentBalance), 
    			this.overdraftLimit);
    }
}
