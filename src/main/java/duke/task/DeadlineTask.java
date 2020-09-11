package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represents the DeadlineTask object. This task object
 * is used to represent a task that has a certain deadline date.
 */
public class DeadlineTask extends DateTask {

    /**
     * Constructs a new DeadlineTask object with the specified task name and date.
     * @param taskName The task's name
     * @param date The task's date
     */
    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName, date);
    }

    /**
     * Returns the string representation of the DeadlineTask.
     * DeadlineTask is represented as "D" in the front and it will also shows
     * the date in the format of MMM dd yyyy HH:mm e.g. Jan 01 2020 18:00.
     * @return A string representation of DeadlineTask
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
