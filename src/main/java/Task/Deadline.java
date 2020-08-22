package Task;

import Helper.DateTimeHelper;
import Task.Task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    private String exactTime;
    public Deadline(String content, LocalDate deadline, String exactTime) {
        super(content);
        this.deadline = deadline;
        this.exactTime = exactTime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String returnStringForm() {
        return "[D]" + super.returnStringForm() + "( by: " + DateTimeHelper.getStringRep(this.deadline) + " " + this.exactTime + ")";
    }
}
