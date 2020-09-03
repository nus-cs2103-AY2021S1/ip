package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    /**
     * Returns a deadline. This is a constructor of deadline
     * @param description describes the event task
     * @param date the time when the event task will happen
     *
     * @return a event task
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public String writeToFile() {
        String isDoneString = this.isDone ? " 1 @ " : " 0 @ ";
        String date = " @ " + this.date;
        return "E @" + isDoneString + this.description + date + System.lineSeparator();
    }

    /**
     * Returns a string that represents the Event task with deadline in the format of MMM dd yyyy.
     *
     * @return String of Event task.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
