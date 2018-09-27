package laboratory07.Submission7_1.workers;

import java.util.GregorianCalendar;

public abstract class HourlyEmployee extends Employee {
	private double hourlyRate;
	private int contractedHour, hoursWorked;
	
	public HourlyEmployee() { this ("", "", null, 0, 0); }

	public HourlyEmployee(String name, String surname, GregorianCalendar startingDate,
			double hourlyRate, int contractedHours) {
		super(name, surname, startingDate);
		this.contractedHour = contractedHours;
		this.hourlyRate = hourlyRate;
		this.hoursWorked = 0;
	}
	
	public double getHourlyRate() { return this.hourlyRate; }
	public int getHoursWorked() { return this.hoursWorked; }
	public int getContractedHour() { return this.contractedHour; }
	
	public void setHoursThisMonth(int hoursWorked) { this.hoursWorked = hoursWorked; }
	
	/**
	 * @return basic monthly wage.
	 */
	public double calculateMonthlyWage() { return this.hourlyRate * this.hoursWorked; }
	
	@Override
	public String toString() { return super.printWorker("hourly"); }
}
