package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    final LocalDate byDate;
    final LocalTime byTime;

    /**
     * Class constructor.
     *
     * @param description The description of task.
     * @param byDate The date of the deadline.
     * @param byTime The time of the deadline.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description, TaskType.DEADLINE);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return byDate;
    }

    /**
     * Return the String representation of the deadline.
     * The deadline is in the format "dd/MM/yyyy HHmm".
     * This is for saving purpose.
     *
     * @return String representation of the deadline.
     */
    public String getBy() { //"21/08/2020 1900" eg
        String time = byTime == null
                ? ""
                : " " + byTime.format(DateTimeFormatter.ofPattern("HHmm"));
        return byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + time;
    }

    /**
     * Returns the String representation of the Deadline task.
     * The deadline is in the format "MMM-dd-yyyy h.mma".
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        String time = byTime == null
                ? ""
                : ", " + byTime.format(DateTimeFormatter.ofPattern("h.mma"));
        //special display of date and time
        return super.toString()
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}