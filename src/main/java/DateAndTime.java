import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateAndTime class is used to allow PandaBot to understand date and time
 * in the format of dd/mm/yyyy hhmm
 */
public class DateAndTime {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a DateAndTime object.
     * @param date
     * @param time
     * @throws DateTimeParseException
     */
    public DateAndTime (String date, String time) throws DateTimeParseException {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }
    
    private String parseDate(LocalDate input) {
        // Day of Month Counter
        String ending;
        int day = date.getDayOfMonth();
        switch (day % 10) {
        case 1:
            ending = "st";
            break;
        case 2:
            ending = "nd";
            break;
        case 3:
            ending = "rd";
            break;
        default:
            ending = "th";
            break;
        }
        
        return day + ending + " of " + date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }
    
    private String parseTime(LocalTime input) {
        if (input.getMinute() == 0) {
            return time.format(DateTimeFormatter.ofPattern("ha"));
        } else {
            return time.format(DateTimeFormatter.ofPattern("h.mma"));
        }
    }
    
    // print date and time 
    @Override
    public String toString() {
        return parseDate(date) + ", " + parseTime(time);
    }
    
}
