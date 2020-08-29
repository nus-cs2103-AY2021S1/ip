package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs deadline object with
     * description and date that is not done.
     * @param description Description of the task.
     * @param date        Event date of the task.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    /**
     * Constructs event object with
     * description, date, and start time that is not done.
     * @param description Description of the task.
     * @param date        Event date of the task.
     * @param startTime   Start time of this event.
     */
    public Event(String description, LocalDate date, LocalTime startTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = null;
    }

    /**
     * Constructs event object with description, date,
     * start time, and end time that is not done.
     * @param description Description of the task.
     * @param date        Event date of the task.
     * @param startTime   Start time of this event.
     * @param endTime     End time of this event.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs event object with description and date.
     * Constructs a done object if isDone is true
     * and not done object otherwise.
     * @param description Description of the task.
     * @param isDone      Indicates whether this task is done or not.
     * @param date        Event date of the task.
     */
    public Event(String description, Boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    /**
     * Constructs event object with description and date.
     * Constructs a done object if isDone is true
     * and not done object otherwise.
     * @param description Description of the task.
     * @param isDone      Indicates whether this task is done or not.
     * @param date        Event date of the task.
     * @param startTime   Start time of this event.
     */
    public Event(String description, Boolean isDone, LocalDate date, LocalTime startTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = null;
    }

    /**
     * Constructs event object with description and date.
     * Constructs a done object if isDone is true
     * and not done object otherwise.
     * @param description Description of the task.
     * @param isDone      Indicates whether this task is done or not.
     * @param date        Event date of the task.
     * @param startTime   Start time of this event.
     * @param endTime     End time of this event.
     */

    public Event(String description, Boolean isDone,
                 LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns E to mark this as an event object.
     * @return E in string.
     */
    public String getType() {
        return "E";
    }

    /**
     * Returns the date of this event.
     * @return Event date in LocalDate.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the start time of this event if it exists.
     * @return Event start time in LocalTime.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of this event if it exists.
     * @return Deadline time in LocalTime.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the string representation of this event object.
     * @return String object of this event.
     */
    @Override
    public String toString() {
        if (this.startTime != null && this.endTime != null) {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " "
                + this.description + " (at:" + this.date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - " + this.endTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";

        } else if (this.startTime != null) {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " "
                + this.description + " (at:" + this.date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";

        } else {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " "
                + this.description + " (at:" + this.date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * Mark this event as done.
     * @return Done version of this event task.
     */
    @Override
    public Event markAsDone() {
        //int index = taskNum - 1;
        if (!this.isDone) {
            Event newTask = new Event(this.getDescription(),
                true, this.date, this.startTime, this.endTime);
            return newTask;
        }
        return this;
    }

}
