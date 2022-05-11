import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Customised Data and Time class to store and print out appropriate
 * dates and times for tasks.
 */
public class DateAndTime {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Construct an date and time object for tasks without specified time.
     */
    public DateAndTime() {
        this.date = null;
        this.time = null;
    }

    /**
     * Construct a date and time object for storage by Duke for tasks with specified timing.
     *
     * @param dateAndTime A string passed to Duke by the user containing time information
     */
    public DateAndTime(String dateAndTime) {

        String date = dateAndTime.substring(0, "2020-02-02".length());
        String time = dateAndTime.substring("2020-02-02".length() + 1);

        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);

    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Overridden toString() method to print out the desired format of date and time.
     *
     * @return A string that represents the date and time stored
     */
    @Override
    public String toString() {
        return date.getYear() + " " +
                date.getMonth() + " " +
                date.getDayOfMonth() + " " +
                date.getDayOfWeek() + " " +
                time.toString() + "hr";
    }


}
