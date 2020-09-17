package main.java.duke.task;
import main.java.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public void editDate(LocalDate newAt) {
        this.at = newAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.getDayOfWeek() + ", "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "E/" + super.saveToFile() + "/" + at;
    }
}
