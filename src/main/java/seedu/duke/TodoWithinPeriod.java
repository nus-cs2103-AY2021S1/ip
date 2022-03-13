package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class extends Task class.
 * Represents a task with a deadline.
 */
public class TodoWithinPeriod extends Task {
    private String startDate;
    private String endDate;
    private LocalDate formattedStartDate;
    private LocalDate formattedEndDate;

    /**
     * Constructor
     * @param name
     * @param status
     * @param startDate
     * @param endDate
     */
    public TodoWithinPeriod(String name, Status status, String startDate, String endDate) {
        super(name, status);
        this.startDate = startDate;
        this.endDate = endDate;
        this.formattedStartDate = LocalDate.parse(startDate);
        this.formattedEndDate = LocalDate.parse(endDate);
    }

    /**
     * String value to represent the object when printed for user.
     * @return String value that represents the Deadline object.
     */
    @Override
    public String toString() {
        return "[T] " + this.getStatus().statusToSymbol() + this.getName()
                + "from: "
                + formattedStartDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
                + " by: "
                + formattedEndDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    /**
     * Generates a String that represents the object to be stored in the data management file.
     * @return A String to be written into the text file.
     */
    @Override
    public String toStore() {
        return "[T] " + this.getStatus().statusToSymbol() + this.getName()
                + "from: "
                + startDate
                + " by: "
                + endDate;

    }

}

