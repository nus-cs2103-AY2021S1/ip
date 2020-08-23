package duke.task.deadline;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of task that deals with deadlines.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructor for the deadline task object.
     *
     * @param name    name of the deadline task
     * @param date    Date when the deadline task is created
     * @param dueDate Date indicated by the user on when the deadline is due.
     */
    public Deadline(String name, LocalDateTime date, String dueDate) {
        super(name, date);
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    /**
     * Overloaded constructor when the deadline task object is re-created from a tasklist.txt file.
     *
     * @param line input from the tasklist.txt file
     */
    public Deadline(String line) {
        super(line);
        this.dueDate = LocalDateTime.parse(line.substring(line.indexOf("(by: ") + 5, line.lastIndexOf(")")),
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    private String getdueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.getdueDate() + ")";
    }
}
