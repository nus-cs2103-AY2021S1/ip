package Task;

import Helper.DateTimeHelper;
import Task.Task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    private String exactTime;
    private String deadlineStr;
    public Deadline(String content, LocalDate deadline, String exactTime, String deadlineStr) {
        super(content);
        this.deadline = deadline;
        this.exactTime = exactTime;
        this.deadlineStr = deadlineStr;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
    public String getDeadlineStr() {
        return this.deadlineStr;
    }
    @Override
    public String returnStringForm() {
        return "[D]" + super.returnStringForm() + "( by: " + DateTimeHelper.getStringRep(this.deadline) + " " + this.exactTime + ")";
    }
}
