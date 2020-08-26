package main.java.duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which extends from the Task class.
 * User can add a Deadline with specific date and time using this class.
 */
public class Deadline extends Task {
    protected LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Method call for a standardise way of storing the Deadline task.
     * @return data of the deadline which can be read in the Storage Class.
     */
    @Override
    public String getState() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description +
                "|" + this.date.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a")) + ")";
    }
}
