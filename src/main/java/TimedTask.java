import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimedTask class is a Task that has a date and time associated
 * with it. Extends from the Task class.
 */
public class TimedTask extends Task {
    protected LocalDateTime dateTime;
    protected String taskType;

    /**
     * Constructor that creates a TimedTask.
     * @param name the name of the Task.
     * @param dateTime the date and time of the Task.
     */
    TimedTask(String name, String dateTime) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.taskType = "TimedTask";
    }

    /**
     * Overloaded constructor that creates a TimeTask with a specified completion status.
     * @param name the name of the Task.
     * @param isDone the completion status of the Task.
     * @param dateTime the date and time of the Task.
     */
    TimedTask(String name, Boolean isDone, String dateTime) {
        super(name, isDone);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.taskType = "TimedTask";
    }

    /**
     * Returns the date and time of the Task.
     * @return the date and time of the Task.
     */
    public String getDateTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Returns the date and time as a LocalDateTime
     * @return the date and time as a LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a String in the format which the TimedTask is saved.
     * @return a String in the format which the TimedTask is saved.
     */
    @Override
    public String encode() {
        String dateTimeFormat = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        return isDone
                ? String.format("%s | 1 | %s | %s", taskType, name, dateTimeFormat)
                : String.format("%s | 0 | %s | %s", taskType, name, dateTimeFormat);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                getDateTimeString());
    }
}
