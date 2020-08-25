package data;

import java.time.LocalDate;

/**
 * Encapsulates an Events task.
 */

public class Events extends Task {

    protected LocalDate at;

    public Events(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}