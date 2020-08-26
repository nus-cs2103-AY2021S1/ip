package task;

import java.time.LocalDate;

public class Event extends Task {
    private final String dateTime;
    private final LocalDate dueDate;
    private final String input;

    public Event(String item, String dateTime, LocalDate dueDate, String input, boolean isCompleted) {
        super(item, isCompleted);
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
