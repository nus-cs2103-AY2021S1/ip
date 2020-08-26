package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Event Task which contains the details of the task
 * as well as the date that the event will occur.
 */
public class Event extends Task {
    private String type = "E";
    private String at;
    private LocalDate date;
    private String time = "";

    /**
     * Constructs a Event Task by providing information such as whether the task
     * is completed, the task name and the event date.
     */
    public Event(String isCompleted, String taskName, String at) {
        super(isCompleted, taskName);
        this.at = at;
        String dateAndTime[] = at.split("\\s");
        this.date = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            // If time is given as well
            this.time = dateAndTime[1].trim();
        }
    }

    /**
     * Overrides the Object toString() method.
     */
    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + " " + this.time + ")";
    }
}
