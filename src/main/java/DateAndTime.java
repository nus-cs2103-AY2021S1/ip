import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Allows PandaBot to understand date and time in the format of dd/mm/yyyy hhmm
 */
public class DateAndTime {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a DateAndTime object by taking in an input 
     * of the format dd/mm/yyyy hhmm
     * 
     * @param date a String of the format dd/mm/yyyy
     * @param time a String of the format hhmm
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

    /**
     * Returns a String representation of the DateAndTime object 
     * in the format date of month year, hour.min AM/PM.
     * 
      * @return a String representation of the DateAndTime object
     */ 
    @Override
    public String toString() {
        return parseDate(date) + ", " + parseTime(time);
    }
    
}
