package Laboratory03.Submission3_1;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Currency;

/**
 * set of JUnit tests for JP2 lab 3
 * BankAccount class defined by
 * students
 * @author Jeremy.Singer@glasgow.ac.uk
 */
public class TestBankAccount {

    private static BankAccount ba;

    private static String POUND_SIGN;

    @Before
    public void setup() {
	// set up an empty BankAccount instance
        ba = new BankAccount();
        POUND_SIGN = Currency.getInstance("GBP").getSymbol();
    }

    @After
    public void teardown() {
        ba = null;
    }

    @Test
    public void testSetAndGetName() {
        String name = "jeremy singer";
        ba.setAccountHolder(name);
        assertTrue("accountHolder getter and setter not working", ba.getAccountHolder().equals(name));

    }

    @Test
    public void testSetAndPrintName() {
        String name = "jeremy singer";
        ba.setAccountHolder(name);
        assertTrue("accountHolder not appearing properly in toString() output", ba.toString().contains(name));
    }


    @Test
    public void testDifferentInstances() {
        BankAccount ba2 = new BankAccount();
        assertFalse("equals() method is faulty (Object.equals() should be fine)", ba.equals(ba2));
    }

    @Test
    public void testDifferentAccountNumbers() {
        for (int i=0; i<100; i++) {
            BankAccount ba2 = new BankAccount();
            assertFalse("no two bank accounts should have the same account number", ba.getAccountNumber() == ba2.getAccountNumber());
        }
    }
    
    // all following methods declared to throw Exception
    // since they call .deposit() and .withdraw() ...
    @Test
    public void testWithdrawal() throws Exception {
        double amount1 = 10.0;
        double amount2 = 11.0;
        ba.deposit(amount1);
        assertFalse("overdraftLimit check not working properly", ba.withdraw(amount2));
    }

    @Test
    public void testWithdrawal2() throws Exception {
        double amount = 10.0;
        ba.deposit(amount);
        assertTrue("overdraftLimit check not working properly (check should be <= not <", ba.withdraw(amount));
    }

    @Test
    public void testChangeOverdraft() throws Exception {
        double amount = 10.0;
        ba.deposit(amount);
        assertTrue(ba.setOverdraftLimit(amount));
        assertTrue("unable to adjust overdraftLimit properly", ba.withdraw(amount));
    }

    @Test
    public void testChangeOverdraft2() throws Exception {
        double amount = 10.0;
        ba.deposit(amount);
        assertTrue(ba.setOverdraftLimit(amount));
        assertTrue("unable to adjust overdraftLimit properly", ba.withdraw(amount));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", ba.withdraw(amount));
    }

    @Test
    public void testChangeOverdraft3() throws Exception {
        double amount = 10.0;
        ba.deposit(amount);
        assertTrue(ba.setOverdraftLimit(amount));
        assertTrue("unable to adjust overdraftLimit properly", ba.withdraw(amount));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", ba.withdraw(amount));
        assertFalse("adjusted overdraftLimit not enforced properly", ba.withdraw(amount));
    }

    @Test
    public void testChangeOverdraft4() throws Exception {
        double amount = 10.0;
        ba.deposit(amount);
        assertTrue(ba.setOverdraftLimit(amount));
        assertTrue("unable to adjust overdraftLimit properly", ba.withdraw(amount));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", ba.withdraw(amount));
        assertFalse("re-adjusteding overdraftLimit to less than current balance should not be allowed", ba.setOverdraftLimit(0));
    }

    @Test(expected = Exception.class)
    public void testDepositNegativeAmount() throws Exception {
        double amount = -1.0;
        ba.deposit(amount);
    }

    @Test(expected = Exception.class)
    public void testWithdrawNegativeAmount() throws Exception {
        double amount = -1.0;
        ba.withdraw(amount);
    }

    // we want toString() to have neatly formatted balances
    // using '£' character
    @Test
    public void testToStringForPositiveBalances() throws Exception {
        double amount1 = 42.0;
        double amount2 = 0.01;
        ba.deposit(amount1);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN /*+ "42.00"*/));
        ba.deposit(amount2);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN + "42.01"));
        ba.withdraw(amount2);
        ba.withdraw(amount2);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN + "41.99"));
    }

    // we want toString() to have neatly formatted balances
    // using '£' character, with '-' sign _before_ currency sign...
    @Test
    public void testToStringForNegativeBalances() throws Exception {
        double amount1 = 10.0;
        double amount2 = 0.01;
        assertTrue(ba.setOverdraftLimit(amount1*2));
        ba.withdraw(amount1);
        assertTrue("toString does not format negative balance correctly", ba.toString().contains("-" + POUND_SIGN + "10.00"));
        ba.deposit(amount2);
        assertTrue("toString does not format positive balance correctly", ba.toString().contains("-" + POUND_SIGN + "9.99"));
    }
    
}
