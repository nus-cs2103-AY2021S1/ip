import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>The DateAndTime class defines the behavior of an object that represents specific time.</p>
 */
public class DateAndTime {
    private final LocalDate date;
    private LocalTime time;

    /** Creates an object to represent date and time as a whole.
     * @param date The date
     * @param time The time
     */
    public DateAndTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public DateAndTime(LocalDate localDate) {
        this.date = localDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        if (time != null) {
            return time.toString() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) ;
        } else  {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
}
