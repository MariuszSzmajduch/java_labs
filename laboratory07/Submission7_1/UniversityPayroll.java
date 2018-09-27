package laboratory07.Submission7_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import laboratory07.Submission7_1.workers.*;

/**
 * JP2 lab 7
 * Main driver class
 * This class sets up some instances of 
 * Employees for a nameless university,
 * then works out the amount required to
 * pay them all for this month.
 * @author jsinger
 */

public class UniversityPayroll {
    
    public static void main(String [] args) {
        Waged [] professors = {
            new SalariedEmployee("Anton",
                                 "Muscatelli",
                                 new GregorianCalendar(2009, 10, 1),
                                 250000), /* https://www.whatdotheyknow.com/request/24757/response/66126/attach/2/RESPONSE%20F0081590.pdf */
            new SalariedEmployee("Albus",
                                 "Dumbledore",
                                 new GregorianCalendar(1881, 1, 1),
                                 50000) /* conversion from galleons, sickles and knuts */
        };

        Waged [] lecturers = {
            new SalariedEmployee("Jeremy",
                                 "Singer",
                                 new GregorianCalendar(2010, 9, 1),
                                 35000) /* i wish */
        };
        
        HourlyEmployee [] otherStaff = {
            new PermanentEmployee("Bob",
                                  "Builder",
                                  new GregorianCalendar(2013, 1, 1),
                                  7.50,
                                  100),
            new TemporaryEmployee("Freda",
                                  "Bloggs",
                                  new GregorianCalendar(2010, 10, 10),
                                  6.31, /* UK minimum wage */
                                  100),
            new TemporaryEmployee("JP2Lab",
                                  "Tutor",
                                  new GregorianCalendar(2013, 10, 1),
                                  5.00,
                                  12)
        };

        for (HourlyEmployee h : otherStaff) {
            h.setHoursThisMonth(120);
        }

        
        List<Waged> allWorkers = new ArrayList<Waged>();

        allWorkers.addAll(Arrays.asList(professors));
        allWorkers.addAll(Arrays.asList(lecturers));
        allWorkers.addAll(Arrays.asList(otherStaff));
        
        double amountToPay = 0;
        for (Waged w : allWorkers) {
            double pay = w.calculateMonthlyWage();
            System.out.printf("%s pay: %.2f\n", w.toString(), pay);
            amountToPay += pay;
        }

        System.out.println("----------");
        System.out.printf("total amount to pay: %.2f\n", amountToPay);
    }
}



