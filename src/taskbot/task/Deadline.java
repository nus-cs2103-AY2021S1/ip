package taskbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    /**
     * Creates an incomplete deadline task
     * @param task Description of task
     * @param by Time to complete by
     */
    public Deadline(String task, LocalDateTime by) {
        super(task, by);
    }

    /**
<<<<<<< HEAD
     * Creates a deadline task
     * @param task Description of task
     * @param by Time to complete by
     */
    public Deadline(String task, LocalDateTime by, boolean isDone) {
        super(task, by, isDone);
    }

    /**
     * Gets the time of the deadline.
     * @return The time of the deadline.
     */
    public LocalDateTime getBy() {
        return getTime();
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Deadline: " + super.getTask() +
                "(by: " + getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")" + "\n";
    }
}
