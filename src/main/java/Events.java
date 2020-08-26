import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Events extends Task {
    private LocalDate startDate;
    private LocalTime startTime;

    public Events(String description, LocalDate startDate, LocalTime startTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public Events(String description, LocalDate startDate, LocalTime startTime, boolean bool) {
        super(description, bool);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    @Override
    public Events markDone() {
        return new Events(this.description, this.startDate, this.startTime, true);
    }

    @Override
    public String toString() {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");
        String formattedDate = this.startDate.format(myDateFormat);
        String formattedTime =this.startTime.format(myTimeFormat);
        if (this.isComplete) {
            return "[E][\u2713] " + this.description + "(at:" + formattedDate + ", " + formattedTime + ")";
        } else {
            return "[E][\u2718] " + this.description + "(at:" + formattedDate + ", " + formattedTime + ")";
        }
    }
}
