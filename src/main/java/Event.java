import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

/**
 * Represents that represents an event of a start date and time.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;

    public Event(String description, LocalDate startDate, LocalTime startTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    private Event(String description, LocalDate startDate, LocalTime startTime, boolean bool) {
        super(description, bool);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * Returns a new Event which is completed.
     * @return Completed Event task.
     */
    @Override
    public Event markDone() {
        return new Event(this.description, this.startDate, this.startTime, true);
    }

    @Override
    public String toString() {
        //Format for the date
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        //Format for the time
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
