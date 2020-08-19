package src.main.java;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String item, String sign, String deadline) {
        super(item, sign);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
