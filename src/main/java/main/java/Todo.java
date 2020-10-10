package main.java;

/**
 * A kind of the tasks, which contains simply a description
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void printDescription() {
        System.out.println("[T][" + getStatusIcon() + "]" + description);
    }

    @Override
    public String getDescription() {
        return "[T][" + getStatusIcon() + "]" + description;
    }
}
