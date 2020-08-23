package taskbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline task
     * @param task Task to be completed
     * @param by Time to complete by
     */
    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    /**
     * Gets the time of the deadline
     * @return LocalDateTime of deadline
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Deadline: " + super.getTask() +
                "(by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")" + "\n";
    }
}
