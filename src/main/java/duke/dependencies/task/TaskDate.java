package duke.dependencies.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Class to handle the parsing of date strings of different format into a valid.
 * Encapsulates a LocalDate object as well.
 *
 */
public class TaskDate implements Serializable, Comparable<TaskDate> {
    /** Some common format for dates */
    private static final DateTimeFormatter US_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    private static final DateTimeFormatter UK_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private LocalDate date;

    /**
     * Returns true if the given date string is in valid format that Duke accepts.
     *
     * @param date date string
     * @return true if date format is valid, false if date format is not valid
     */
    public static boolean isValidFormat(String date) {
        try {
            if (date.contains("/")) {
                LocalDate.parse(date, UK_DATE_FORMAT);
            } else {
                LocalDate.parse(date);
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Constructor for a TaskDate. Specified date string has to be in correct format.
     * Acceptable formats:
     * 1. MM/dd/uuuu
     * 2. uuuu-MM-dd
     *
     * @param date date string in correct format
     */
    public TaskDate(String date) {
        if (date.contains("/")) {
            LocalDate date1 = LocalDate.parse(date, UK_DATE_FORMAT);
            this.date = date1;
        } else {
            LocalDate date1 = LocalDate.parse(date);
            this.date = date1;
        }
    }

    @Override
    public int compareTo(TaskDate o) {
        if (this.date.equals(o.date)) {
            return 0;
        } else if (this.date.isBefore(o.date)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    /**
     * Returns date in string format e.g. "Oct 05 2020"
     */
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
    }


//    public static void main(String[] args) {
        //create dates from strings
//        LocalDate d1 = LocalDate.parse("2019-12-01");
//        LocalDate d2 = LocalDate.parse("2019-12-02");
//        LocalDate d3 = LocalDate.parse("2019-12-02");
//        LocalDate.parse(
//                "30/12/2010" ,
//                DateTimeFormatter.ofPattern( "dd/MM/uuuu" )
//        );
//
//        //compare dates
//        System.out.println(d1.isBefore(d2)); // -> true
//        System.out.println(d1.isAfter(d2)); // -> false
//        System.out.println(d2.equals(d3)); // -> true
//
//        //work with dates
//        System.out.println(d1.getDayOfWeek()); // -> SUNDAY
//        System.out.println(d1.getMonth()); // -> DECEMBER
//        System.out.println(d1.plus(1, ChronoUnit.YEARS));  // -> 2020-12-01
//
//        // get today's date and print it in a specific format
//        LocalDate d4 = LocalDate.now();
//        System.out.println(d4); // -> 2019-10-15
//        System.out.println(d4.format(DateTimeFormatter.ofPattern("MMM d uuuu")) + " HI"); // -> Oct 15 2019
//        System.out.println(TaskDate.isValidFormat("2019-05-15"));
//        TaskDate d1 = new TaskDate("2019-05-08");
//        TaskDate d2 = new TaskDate("20/08/2020");
//        TaskDate d3 = new TaskDate("20/08/2020");
//        System.out.println(d1.compareTo(d3));
//    }

}
