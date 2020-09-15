package com.siawsam.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    /** The date of the deadline */
    private LocalDate date;

    /**
     * Constructs a Deadline instance containing a description and date.
     *
     * @param description A string describing the deadline.
     * @param date A string representation of the deadline's date.
     * @throws DateTimeParseException if the date string given doesn't follow ISO8601 format.
     */
    public Deadline(String description, String date) throws DateTimeParseException {
        super(description);
        parseDate(date);
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date An ISO8601-compliant date string.
     * @throws DateTimeParseException if the date string given doesn't follow ISO8601 format.
     */
    void parseDate(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns a string representation of the deadline containing its description and formatted date.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        String formattedDate = date.format(formatter);
        return "[D] " + super.toString() + " (by: " + formattedDate + ")";
    }
}
