package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String endTime;
    private LocalDateTime formattedDateTime;
    private LocalDate formattedDate;

    public Deadline(String description, String endTime) throws InvalidDescriptionException, InvalidTimeException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! Deadline description shouldn't be blank.");
        } else if (endTime.isBlank()) {
            throw new InvalidTimeException("Do try again by adding a time you need to get this done by.");
        } else {
            this.endTime = endTime;
            formatEndTime();
        }
    }

    public Deadline(String description, String endTime, boolean isDone) {
        super(description, isDone);
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

    @Override
    public String toString() {
        return this.formattedDateTime == null
                ? this.formattedDate == null
                ? "[D]" + super.toString() + " (by: " + this.endTime + ")"
                : "[D]" + super.toString() + " (by: " +
                this.formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[D]" + super.toString() + " (by: " +
                this.formattedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
    }

    @Override
    public String databaseString() {
        return "D | " + super.databaseString() + " | " + this.endTime;
    }
}
