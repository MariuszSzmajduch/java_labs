package laboratory07.Submission7_1.workers;

import java.util.GregorianCalendar;

public class TemporaryEmployee extends HourlyEmployee {
	
	public TemporaryEmployee() { this ("", "", null, 0, 0); }

	public TemporaryEmployee(String name, String surname, GregorianCalendar startingDate, 
			double hourlyRate, int contractedHours) {
		super(name, surname, startingDate, hourlyRate, contractedHours);
	}
	
	@Override
	public String toString() { return super.printWorker("temp"); }
}
