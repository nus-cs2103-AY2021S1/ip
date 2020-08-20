package Task;

import Task.Task;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String returnStringForm() {
        return "[D]" + super.returnStringForm() + "( by: " + this.deadline + ")";
    }
}
