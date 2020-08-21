package main.java;

import java.util.Date;

public class DeadlineTask extends Task {
    protected String deadline;
    protected String symbol = "[D]";

    public DeadlineTask(String deadline, String task) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return symbol + super.toString() + " (by: " + deadline + ")";
    }
}
