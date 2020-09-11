package duke.task;

import java.time.LocalDate;

/**
 * Represents an event that can be added to Duke's Task List.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Constructs an event with given description and date.
     * @param description  String of the description of the event.
     * @param date  String of the date of the event in YYYY-MM-DD format.
     */
    public Event(String description, String date) {
        super(description);
        assert date.length() != 0 : "date is empty";
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Overloaded constructor that constructs the event with done status.
     * @param description  String of the description of the event.
     * @param date  String of the date of the event in YYYY-MM-DD format.
     * @param isDone  Whether the event is already done.
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        assert date.length() != 0 : "date is empty";
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    /**
     * String representation of the event.
     * @return  String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(PRINT_DATE_TIME_FORMAT) + ")";
    }

    /**
     * String representation of the event to be written to a file.
     * @return  String representation of the event to be written to a file.
     */
    @Override
    public String getFileSaveText() {
        return "E " + super.getFileSaveText() + " | " + this.date.format(INPUT_DATE_TIME_FORMAT);
    }
}
