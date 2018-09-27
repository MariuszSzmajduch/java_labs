package laboratory07.Submission7_1.workers;

import java.util.GregorianCalendar;

public class SalariedEmployee extends Employee {
	/**
	 * Number of months in a year.
	 */
	private static int MONTHS = 12;
	private double annualSalary;
	
	public SalariedEmployee() { this ("", "", null, 0); }

	public SalariedEmployee(String name, String surname, GregorianCalendar startingDate, double annualSalary) {
		super(name, surname, startingDate);
		this.annualSalary = annualSalary;
	}

	/**
	 * @return estimated monthly wage based on annual salary.
	 */
	@Override
	public double calculateMonthlyWage() { return this.annualSalary/MONTHS; }
	
	@Override
	public String toString() { return super.printWorker("salaried"); }
}
