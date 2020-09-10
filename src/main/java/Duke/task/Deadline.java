package duke.task;

import java.time.LocalDate;

/**
 * Represents a deadline that can be added to Duke's Task List.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs a deadline with given description and date.
     * @param description  String of the description of the deadline.
     * @param date  String of the date of the deadline in YYYY-MM-DD format.
     */
    public Deadline(String description, String date) {
        super(description);
        assert date.length() != 0 : "date is empty";
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Overloaded constructor that constructs the deadline with done status.
     * @param description  String of the description of the deadline.
     * @param date  String of the date of the deadline in YYYY-MM-DD format.
     * @param isDone  Whether the deadline is already done.
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        assert date.length() != 0 : "date is empty";
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    /**
     * String representation of the deadline.
     * @return  String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(PRINT_DATE_TIME_FORMAT) + ")";
    }

    /**
     * String representation of the deadline to be written to a file.
     * @return  String representation of the deadline to be written to a file.
     */
    @Override
    public String getFileSaveText() {
        return "D " + super.getFileSaveText() + " | " + this.date.format(INPUT_DATE_TIME_FORMAT);
    }
}
