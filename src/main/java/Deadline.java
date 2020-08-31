import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a due date and time.
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    private Deadline(String description, LocalDate dueDate, LocalTime dueTime, boolean bool) {
        super(description, bool);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Returns a Deadline event that is completed.
     * @return Completed Deadline task.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(this.description, this.dueDate, this.dueTime, true);
    }

    @Override
    public String toString() {
        //Format for the date
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        //Format for the time
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

        String formattedDate = this.dueDate.format(myDateFormat);
        String formattedTime =this.dueTime.format(myTimeFormat);

        if (this.isComplete) {
            return "[D][\u2713] " + this.description + "(by:" + formattedDate + ", " +  formattedTime + ")";
        } else {
            return "[D][\u2718] " + this.description + "(by:" + formattedDate + ", " + formattedTime + ")";
        }
    }
}
