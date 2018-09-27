package laboratory07.Submission7_1.workers;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Employee implements Waged {
	static int workerID = 0;
	private int employeeID;
	private String name, surname;
	Calendar startingDate;
	
	public Employee() { this("", "", null); }
	
	public Employee(String name, String surname, GregorianCalendar startingDate) {
		this.name = name;
		this.surname = surname;
		this.startingDate = startingDate;
		this.employeeID = ++workerID;
	}
	
	public int getEmployeeID() { return employeeID; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public Calendar getStartingDate() { return startingDate; }
	
	/**
	 * 
	 * @param s - type of worker (permanent, salaried, etc)
	 * @return description of a worker
	 */
	protected String printWorker(String s) { 
		return String.format("%s %s (%s,  #%d, since %d) ", 
				this.name, this.surname, s, this.employeeID, this.startingDate.get(1)); 
		}
}
