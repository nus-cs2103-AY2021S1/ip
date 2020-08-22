package main.java;

import java.util.Date;

public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String deadline, String task, TaskSymbol taskType) {
        super(task, taskType);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
