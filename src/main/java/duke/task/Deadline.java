package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Creates a Deadline.
     * @param name A string representing the name of the deadline.
     * @param isCompleted A boolean value representing whether the deadline has been completed.
     * @param date A LocalDate representing the date of the deadline.
     */
    protected Deadline(String name, boolean isCompleted, LocalDate date) {
        super(name, isCompleted);
        this.date = date;
    }

    /**
     * Creates a new Deadline.
     * The completion status is false by default.
     * @param name A string representing the name of the new deadline.
     * @param date A LocalDate representing the date of the new deadline.
     * @return The new deadline created.
     */
    public static Deadline newDeadline(String name, LocalDate date){
        return new Deadline(name, false, date);
    }

    /**
     * Creates a Deadline using information in storage.
     * The deadline created is an already previously existing deadline with its details recorded in storage.
     * Usually called when starting up the application, to populate the TaskList.
     * @param name A string representing the name of the existing deadline.
     * @param isCompleted A boolean value representing whether the deadline has been completed.
     * @param date A LocalDate representing the date of the existing deadline.
     * @return The existing deadline created.
     */
    public static Deadline existingDeadline(String name, boolean isCompleted, LocalDate date){
        return new Deadline(name, isCompleted, date);
    }

    /**
     * Gets whether the deadline date is set as today.
     * @return The boolean value representing whether the deadline is today.
     */
    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    /**
     * Gets the string representation of the deadline object for printing.
     * This contains the completion status and the deadline name, date, as well as the type (Deadline).
     * @return A string representation of the deadline for printing.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }

    /**
     * Gets the string representation of the deadline object to be saved in storage.
     * This contains the completion status and the deadline name, date, as well as the type (Deadline).
     * The different fields are separated by a "|" character.
     * Completion status is represented by a 1 or 0.
     * @return A string representation of the deadline object to be saved in storage.
     */
    public String toSaveString(){
        return "D" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
