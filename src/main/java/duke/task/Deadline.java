package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Supports the creation of duke.task.Deadline objects.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates a duke.task.Deadline object
     * @param description Description of task.
     * @param dateAndTime Date and time of the task.
     * @param taskType Type of task.
     */
    public Deadline(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[1].split("/");
        this.date = LocalDate.of(Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]));
        String timeUnformatted = dateAndTimeSplit[2];
        String timeFormatted = timeUnformatted.substring(0, 2)
                + ":"
                + timeUnformatted.substring(2, 4);
        this.time = LocalTime.parse(timeFormatted);
    }

    /**
     * Creates a duke.task.Deadline object with extra parameters that defines date and time separately, and whether
     * task is done or not.
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     * @param date Date of task.
     * @param time Time that task is due.
     */
    public Deadline(String description, TaskType taskType, boolean isDone, LocalDate date, LocalTime time) {
        super(description, taskType, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String buildSaveString() {
        return super.buildSaveString() + "/" + this.date + " " + this.time;
    }

    /**
     * Returns string representation of the duke.task.Deadline object.
     *
     * @return String representation of the duke.task.Deadline object.
     */
    @Override
    public String toString() {
        return "[Deadline]"
                + super.toString() + " "
                + "(by: " + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + " " + this.time + ") "
                + "- Priority: " + super.getPriorityLevel();
    }
}
