package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    /**
     * Returns a deadline. This is a constructor of deadline.
     * @param description describes the deadline task
     * @param date the deadline of the task
     *
     * @return a deadline task
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public String writeToFile() {
        String isDoneString = this.isDone ? " 1 @ " : " 0 @ ";
        String date = " @ " + this.date;
        return "D @" + isDoneString + this.description + date + System.lineSeparator();
    }

    /**
     * Returns a string that represents the Deadline task with deadline in the format of MMM dd yyyy.
     *
     * @return String of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
