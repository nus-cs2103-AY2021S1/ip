package main.java.task;

public class TodoTask extends Task {
    private static final String TODO = "[T]";

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask() {}

    @Override
    public String toString() {
        return TODO + super.toString();
    }
}
