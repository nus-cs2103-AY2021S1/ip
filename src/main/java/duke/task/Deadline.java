package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline Task which contains the details of the task
 * as well as the date that the task should be completed by.
 */
public class Deadline extends Task {
    private String type = "D";
    private String by;
    private LocalDate date;
    private String time = "";

    /**
     * Constructs a Deadline Task by providing information such as whether the task
     * is completed, the task name and the date of the deadline.
     */
    public Deadline(String isCompleted, String taskName, String by) {
        super(isCompleted, taskName);
        this.by = by;
        // In case time is included
        String dateAndTime[] = by.split("\\s");
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
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + this.time + ")";
    }
}
