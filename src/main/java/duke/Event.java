package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;

    /**
     * Returns a deadline. This is a constructor of deadline
     *
     * @param description describes the event task
     * @param date        the time when the event task will happen
     * @return a event task
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string that represents the Event task which will be written in the documented file.
     *
     * @return String represents Event task in duke.txt.
     */
    public String writeToFile() {
        String date = " @ " + this.date;
        return "E @" + super.writeToFile() + date + System.lineSeparator();
    }

    /**
     * Returns a string that represents the Event task with deadline in the format of MMM dd yyyy.
     *
     * @return String of Event task.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
