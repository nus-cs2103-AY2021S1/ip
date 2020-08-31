package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS! The deadline in an incorrect format! "
                    + "Please indicate the date as <yyyy-mm-dd>");
        }
    }

    public String getParsedTask() {
        return "deadline " + this.description + " /by " + this.by + System.lineSeparator() 
                + this.isDone + System.lineSeparator();
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.description.equals(otherDeadline.description)
                    && this.isDone == otherDeadline.isDone
                    && this.by.equals(otherDeadline.by);
        } else {
            return false;
        }
    }
}
