package dukeclass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of task.
 * The task has a String message and a Boolean status.
 * in addition to that, it has a preposition and a timeline to be completed by
 */
public class Deadline extends Task {

    private static final String ICON = "D";
    private String preposition;
    private LocalDateTime dateTime;


    /**
     * Constructor for Deadline task, status is set to false by default
     *
     * @param taskString  the task given by the user
     * @param preposition  the preposition for the time frame
     * @param dateTimeString  the time frame in a specific format of yyyy-MM-dd HH:mm
     */
    public Deadline(String taskString, String preposition, String dateTimeString) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Constructor for Deadline task but with ability to set status
     *
     * @param taskString  the task given by the user
     * @param preposition  the preposition for the time frame
     * @param dateTimeString  the time frame in a specific format of yyyy-MM-dd HH:mm
     * @param status  status of the task
     */
    public Deadline(String taskString, String preposition, String dateTimeString, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.status = status;
    }

    /**
     * Constructor for Deadline task but with ability to set status
     * and take in dateTime as LocalDateTime instead of string
     *
     * @param taskString  the task given by the user
     * @param preposition  the preposition for the time frame
     * @param dateTime  the time frame
     * @param status  status of the task
     */
    public Deadline(String taskString, String preposition, LocalDateTime dateTime, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = dateTime;
        this.status = status;
    }

    /**
     * Snooze the task by one day
     *
     * @return a new Task with the new dateTime.
     */
    public Task snoozeTask() {

        LocalDateTime newDateTime = this.dateTime.minusDays(-1);

        return new Deadline(this.taskString, this.preposition, newDateTime, this.status);
    }

    /**
     * Snooze the task by the given days
     *
     * @param days number of days to snooze the task
     * @return a new Task with the new dateTime.
     */
    public Task snoozeTask(int days) {

        LocalDateTime newDateTime = this.dateTime.minusDays(-1 * days);

        return new Event(this.taskString, this.preposition, newDateTime, this.status);
    }


    @Override
    public String toString() {
        String statusIcon = (status) ? "\u2714" : "\u2716";
        return "[" + ICON + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": "
                + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

    @Override
    public String toDataString() {
        return "deadline//" + taskString + "//" + preposition + "//"
                + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "//" + status;
    }
}
