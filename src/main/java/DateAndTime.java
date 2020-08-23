import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    private final LocalDate date;
    private LocalTime time;

    public DateAndTime(LocalDate localDate, LocalTime time) {
        this.date = localDate;
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
