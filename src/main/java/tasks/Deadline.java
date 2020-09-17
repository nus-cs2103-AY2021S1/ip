package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.InvalidDescriptionException;
import exceptions.InvalidTimeException;

/**
 * Represents a deadline, consisting of a description and an end time.
 * Throws InvalidDescriptionException or InvalidTimeException if
 * the description or end time provided is blank.
 */
public class Deadline extends Task {

    private final String endTime;
    private LocalDateTime formattedDateTime;
    private LocalDate formattedDate;

    /**
     * Creates a new deadline object with a given description and end time.
     * @param description provided for the deadline
     * @param endTime provided for the deadline
     * @throws InvalidDescriptionException when description is empty
     * @throws InvalidTimeException when end time is empty
     */
    public Deadline(String description, String endTime) throws InvalidDescriptionException, InvalidTimeException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException(
                    "Hey! Deadline description shouldn't be blank.");
        } else if (endTime.isBlank()) {
            throw new InvalidTimeException(
                    "Do try again by adding a time you need to get this done by.");
        } else {
            this.endTime = endTime;
            formatEndTime();
        }
    }

    /**
     * Creates a new deadline object given a description, deadline and its done status.
     * @param description provided for the deadline
     * @param endTime provided for the deadline
     * @param isDone provided for the deadline
     */
    public Deadline(String description, String endTime, boolean isDone, String tags) {
        super(description, isDone, tags);
        this.endTime = endTime;
        formatEndTime();
    }

    private void formatEndTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.formattedDateTime = LocalDateTime.parse(this.endTime, formatter);
        } catch (DateTimeParseException e1) {
            this.formattedDateTime = null;
            try {
                this.formattedDate = LocalDate.parse(this.endTime);
            } catch (DateTimeParseException e2) {
                this.formattedDate = null;
            }
        }
    }

    /**
     * Obtains the formatted date of the deadline.
     * @return formatted date
     */
    @Override
    public LocalDate getDate() {
        return this.formattedDate;
    }

    /**
     * Obtains the formatted date and time of the deadline.
     * @return formatted date and time
     */
    @Override
    public LocalDateTime getDateTime() {
        return this.formattedDateTime;
    }

    /**
     * Returns the string that represents the deadline.
     *
     * @return the string consisting of the tag,
     * done status, description and end time
     */
    @Override
    public String toString() {
        return this.formattedDateTime == null
                ? this.formattedDate == null
                    ? "[D]" + super.toString() + " (by: " + this.endTime + ")"
                    : "[D]" + super.toString() + " (by: "
                        + this.formattedDate.format(
                                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[D]" + super.toString() + " (by: "
                    + this.formattedDateTime.format(
                            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
    }

    /**
     * Returns the string that represents the deadline in a database.
     *
     * @return the string consisting of the tag,
     * done status, description and end time
     */
    @Override
    public String databaseString() {
        return "D | " + super.databaseString() + " | " + this.endTime;
    }
}
