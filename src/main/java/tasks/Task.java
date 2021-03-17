package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a task with a datetime and description
 */
public class Task {
    private boolean done;
    private String work;
    private LocalDateTime date;

    /**
     * Initializes a task with the user given description
     *
     * @param work A String with the user description
     */
    public Task(String work) {
        this.done = false;
        this.work = work;
    }

    /**
     * Initializes a task with user given description and datetime
     *
     * @param work A String containing the user given description
     * @param date A LocalDateTime object containing the timing of the deadline/task
     */
    public Task(String work, LocalDateTime date) {
        assert work.length() != 0 && date != null : "The input cannot be empty "
                + "and date cannot be null";
        this.work = work;
        this.date = date;
        this.done = false;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Get a description of the task
     *
     * @return String containing the description
     */
    public String description() {
        return done
                ? "[T][0]"
                : "[T][1]";
    }
    public void updateStatus() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public String getWork() {
        return work;
    }

    public boolean istodo() {
        return false;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Overrides the toString method
     *
     * @return String representing the task
     */
    public String toString() {
        if (!this.done) {
            return "[Not done]" + this.work;
        } else {
            return "[Donezo]" + this.work;
        }
    }
}
