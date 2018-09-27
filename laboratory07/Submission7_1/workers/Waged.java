package laboratory07.Submission7_1.workers;

/**
 * Every member of the company who is
 * on the payroll is a Waged worker.
 * This means we can compute the monthly
 * pay due to that worker.
 * @author jsinger
 */
public interface Waged {
    /**
     * compute the monthly wage 
     * packet (in pounds) due
     * for a particular worker
     */
    double calculateMonthlyWage();
}