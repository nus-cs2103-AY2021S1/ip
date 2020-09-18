package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task to be done
 *
 * @author Ryan Lim
 */
public class Task {

    enum Status {
        COMPLETED,
        INCOMPLETE
    }

    private final String description;
    private Status status = Status.INCOMPLETE;
    private final LocalDateTime dueDate;

    /**
     * constructor for tasks that was saved into hard disk and mounted onto duke
     * @param description description of the task
     * @param dueDate the due date of the task if any
     * @param done the status of the task (complete or incomplete)
     */
    public Task (String description, LocalDateTime dueDate, boolean done) {
        this.description = description;
        this.status = done ? Status.COMPLETED : Status.INCOMPLETE;
        this.dueDate = dueDate;
    }

    /**
     * constructor for new task that is created through the duke UI
     * @param description description of the task
     * @param dueDate the due date of the task if any
     */
    public Task (String description, LocalDateTime dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public void markDone() {
        this.status = Status.COMPLETED;
    }

    public boolean isDone() {
        return this.status == Status.COMPLETED;
    }

    /**
     * converts the task into string with a specific format to be written onto the hard disk
     * @return the String representation of task in a specific format
     */
    public String writeToFile() {
        return (this.status == Status.COMPLETED ? "1" : "0")
                + "|" + this.description.strip();
    }

    public boolean isDueOn(LocalDate date) {
        return this.dueDate.toLocalDate().isEqual(date); }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getDateString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")).toString();
    }

    public String toString() {
        return (this.status == Status.COMPLETED ? "[\u2713]" : "[\u2718]") + this.description;
    }

}
