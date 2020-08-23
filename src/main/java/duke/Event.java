package duke;

import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;

    public Event(String name, String dateTime) {
        super(name);
        this.date = LocalDate.parse(dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getItemName() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
