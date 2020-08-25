package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public LocalDate getDate() {
        return this.deadlineDate;
    }

    @Override
    public String taskFileString() {
        return "D | " + (super.isDone ? "1 |" : "0 |") + super.description + " | " +
                this.deadlineDate.toString() + " "+ this.deadlineTime.toString();
    }

    @Override
    public String toString() {
        String formattedEventTime = this.deadlineDate.format(DateTimeFormatter.ofPattern("EE, MMM dd yyyy")) + ", " +
                this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedEventTime + ")";
    }
}
