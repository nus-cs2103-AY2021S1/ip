package duck.task;

import duck.ui.Colour;

import java.time.LocalDate;

/**
 * Deadline class for representing the deadline type.
 */
public class Deadline extends TaskWithDate {

    public Deadline(String desc, LocalDate date) {
        super(desc, date);
    }

    @Override
    public String getStatus() {
        return Colour.Magenta("[D]") + super.getStatus() + " (by: " + super.getDateAsString() + ")";
    }
}
