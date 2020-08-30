package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Task happening on a Date.
 */
public class Event extends Task {
    final LocalDate atDate;
    final LocalTime atTime;

    /**
     * Class constructor.
     *
     * @param description The description of task.
     * @param atDate The date of the task.
     * @param atTime The time of the task.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description, TaskType.EVENT);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Returns the date of the task.
     *
     * @return The date of the task.
     */
    public LocalDate getDate() {
        return atDate;
    }

    /**
     * Returns the String representation of the occurrence of the event.
     * The occurrence is in the format "dd/MM/yyyy HHmm".
     * This is for saving purpose.
     *
     * @return The String representation of the occurrence of the event.
     */
    public String getAt() { //"21/08/2020 1900" eg
        String time = atTime == null
                ? ""
                : " " + atTime.format(DateTimeFormatter.ofPattern("HHmm"));
        return atDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + time;
    }

    /**
     * Returns the String representation of the Event task.
     * The occurrence is in the format "MMM-dd-yyyy h.mma".
     *
     * @return The String representation of the occurrence of the event.
     */
    @Override
    public String toString() {
        String time = atTime == null
                        ? ""
                : ", " + atTime.format(DateTimeFormatter.ofPattern("h.mma"));
        //special display of date and time
        return super.toString()
                + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}