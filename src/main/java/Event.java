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

    @Override
    public Event changeDesc(String newDesc) {
        return new Event(newDesc, this.startDate, this.startTime, this.isComplete);
    }

    public Event changeTime(LocalTime newStartTime) {
        return new Event(this.description, this.startDate, newStartTime, this.isComplete);
    }

    public Event changeDate(LocalDate newStartDate) {
        return new Event(this.description, newStartDate, this.startTime, this.isComplete);
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
        //Format for the date in day, dd MMM yyyy, e.g. Sat, 25 Jan 2020
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        //Format for the time in XX:XX aa, e.g. 11:59 pm
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
