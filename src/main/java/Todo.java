package main.java;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printDescription() {
        System.out.println("[T][" + getStatusIcon() + "]" + description);
    }
}
