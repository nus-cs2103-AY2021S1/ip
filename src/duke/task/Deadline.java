package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected String date2;

    // Constructor for Deadline
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
        this.date2 = date;
    }

    // Get info to store in hard disk
    @Override
    public String[] getInfo() {
        return new String[] {"D", description, date2};
    }

    // Return string representation of Deadline
    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " [D]" + super.toString() + " (by: " + dateString + ")";
    }
}
