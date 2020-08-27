package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of Task that can be added to the TaskList.
 *
 * @author Joshua
 */
public class Deadline extends Task {

    /**
     * NEW_DATETIME_FORMAT is the date time format that will be displayed to the user.
     * SAVE_READ_DATETIME_FORMAT is the date time format that is stored internally in Duke.
     */
    private final static DateTimeFormatter NEW_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mma");
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates the Deadline with the given task description.
     *
     * @param taskDescription the description given by the user for this task
     */
    public Deadline(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Sets the deadline for the task.
     *
     * @param givenDate the deadline the task should be completed by.
     */
    public void setTime(LocalDateTime givenDate) {
        date = givenDate;
    }

    /**
     * Formats the Deadline Task into the format it is stored as.
     *
     * @return the formatted deadline task.
     */
    @Override
    public String saveFormat() {
        String base = "[D] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "by:" + date.format(SAVE_READ_DATETIME_FORMAT);
        return base;
    }

    /**
     * Returns the deadline to the ui to be displayed to the user.
     *
     * @return a String that contains the deadline and date in the display format.
     */
    @Override
    public String toString() {
        String base = "[D] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "(by:" + date.format(NEW_DATETIME_FORMAT) + ")";
        return base;
    }
}
