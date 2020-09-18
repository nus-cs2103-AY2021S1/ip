package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Supports the creation of Event objects.
 */
public class Event extends Task {

    /**
     * The date of the event.
     */
    protected LocalDate date;

    /**
     * The time the event starts.
     */
    protected LocalTime timeStart;

    /**
     * The time the event ends.
     */
    protected LocalTime timeEnd;

    /**
     * Creates an Event object
     * @param description Description of task.
     * @param dateAndTime Date and time of the task.
     * @param taskType Type of task.
     */
    public Event(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[1].split("/");
        this.date = LocalDate.of(Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]));
        String[] timeSplit = dateAndTimeSplit[2].split("-");
        String timeStartUnformatted = timeSplit[0];
        String timeStartFormatted = timeStartUnformatted.substring(0, 2)
                + ":"
                + timeStartUnformatted.substring(2, 4);
        String timeEndUnformatted = timeSplit[1];
        String timeEndFormatted = timeEndUnformatted.substring(0, 2)
                + ":"
                + timeEndUnformatted.substring(2, 4);
        this.timeStart = LocalTime.parse(timeStartFormatted);
        this.timeEnd = LocalTime.parse(timeEndFormatted);
    }

    /**
     * Creates an Event object with extra parameters that defines date and time separately, and whether
     * task is done or not.
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     * @param date Date of task.
     * @param timeStart Starting time of task.
     * @param timeEnd Ending time of task.
     */
    public Event(String description, TaskType taskType, boolean isDone, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(description, taskType, isDone);
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Adds the necessary formatting for a Event.
     * @return Formatted string for saving.
     */
    @Override
    public String buildSaveString() {
        return super.buildSaveString() + "/" + this.date + " " + this.timeStart + "-" + this.timeEnd;
    }

    /**
     * Returns string representation of the Event object.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[Event]" + super.toString() + " "
                + "(at: " + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + " " + this.timeStart + "-" + this.timeEnd + ") "
                + "- Priority: " + super.getPriorityLevel();
    }
}
