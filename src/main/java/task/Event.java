package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String dateTime;
    private LocalDate dueDate;
    private String input;

    public Event(String item, String dateTime, LocalDate dueDate, String input, boolean completed) {
        super(item, completed);
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.input = input;
    }

    @Override
    public String getItem() {
        return "[E]" + super.getItem() + "(at: " + dateTime + ")";
    }

    @Override
    public String getInput() {
        return "[E]" + super.getItem() + "/at " + this.input;
    }
}
