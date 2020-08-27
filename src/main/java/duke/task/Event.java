package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at);
        this.dateString = at;
    }

    @Override
    public String toFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "E | " + doneInteger + " | " + this.description + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
