package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Supports the creation of duke.task.Event objects.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;

    /**
     * Creates an duke.task.Event object
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
     * Creates an duke.task.Event object with extra parameters that defines date and time separately, and whether
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

    @Override
    public String buildSaveString() {
        return super.buildSaveString() + "/" + this.date + " " + this.timeStart + "-" + this.timeEnd;
    }

    /**
     * Returns string representation of the duke.task.Event object.
     *
     * @return String representation of the duke.task.Event object.
     */
    @Override
    public String toString() {
        return "[Event]" + super.toString() + " "
                + "(at: " + this.date + " " + this.timeStart + "-" + this.timeEnd + ") "
                + "Priority: " + super.getPriorityLevel();
    }
}
