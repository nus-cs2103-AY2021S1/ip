package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private String dateTime;
    private LocalDate dueDate;
    private String input;

    public Deadline(String item, String dateTime, LocalDate dueDate, String input, boolean completed) {
        super(item, completed);
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
