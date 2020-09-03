package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task of "deadline" category
 */

public class Deadline extends Task {

    protected LocalDate dateBy;

    public Deadline(int category, int status, String command, String date) {
        super(category, status, command);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateBy = LocalDate.parse(date, inputFormat);
    }

    /**
     * Prints out the deadline task and converts the date format
     * @return string representation
     */

    @Override
    public String toString() {
        String date = dateBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + "(by: " + date + ")";
    }
}