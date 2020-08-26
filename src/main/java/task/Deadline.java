package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final String dateTime;
    private final LocalDate dueDate;
    private final String input;

    public Deadline(String item, String dateTime, LocalDate dueDate, String input, boolean isCompleted) {
        super(item, isCompleted);
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.input = input;
    }

    @Override
    public String getInput() {
        return "[D]" + super.getItem() + "/by " + this.input;
    }

    @Override
    public String getItem() {
        return "[D]" + super.getItem() + "(by: " + dateTime + ")";
    }
}
