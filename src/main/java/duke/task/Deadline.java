package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    LocalDate date;
    LocalTime time;

    public Deadline(String task, LocalDate date, LocalTime time) {
        super(task);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a Deadline with the appropriate details. Should only be used when reading from
     * the storage file.
     * 
     * @param task Task description.
     * @param inputDate String representing the date in the format YYYY-MM-DD
     * @param inputTime String representing the time in the format HH:MM
     * @param done Status of the task.
     * @return A Deadline instance representing the deadline.
     */
    public static Deadline of(String task, String inputDate, String inputTime, boolean done) {
        try {
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime time = LocalTime.parse(inputTime, DateTimeFormatter.ISO_LOCAL_TIME);
            Deadline deadline = new Deadline(task, date, time);
            if (done) {
                deadline.setDone();
            }
            return deadline;
        } catch (DateTimeException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Returns a String representation of the deadline to be stored in the 
     * storage file.
     *
     * @return Formatted String representing the deadline.
     */
    @Override
    public String toDataString() {
        return "D // " + (done ? "1": "0") + " // " + task + " // "
            + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " // "
            + time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
    
    /**
     * Returns a String representation of the deadline for display. The date and
     * time substring is in the format Mmm DD, YYYY H:MM AM/PM.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            + " " + this.time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
            + ")";
    }
}
