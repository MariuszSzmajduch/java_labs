package laboratory07.Submission7_1.workers;

import java.util.GregorianCalendar;

public class PermanentEmployee extends HourlyEmployee {
	private static double OVERTIME_BONUS = 0.5;
	
	public PermanentEmployee() { this ("", "", null, 0, 0); }

	public PermanentEmployee(String name, String surname, GregorianCalendar startingDate, 
			double hourlyRate, int contractedHours) {
		super(name, surname, startingDate, hourlyRate, contractedHours);
	}
	
	/**
	 * @return basic monthly wage increased by an overtime bonus.
	 */
	@Override
	public double calculateMonthlyWage() {
		int overtime = this.getHoursWorked() - this.getContractedHour();
		return super.calculateMonthlyWage() + 
				OVERTIME_BONUS * this.getHourlyRate() * (overtime > 0 ? overtime : 0);
	}
	
	@Override
	public String toString() { return super.printWorker("permanent"); }
}
