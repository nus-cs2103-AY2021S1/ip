package main.java;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[T]" + "[" + finished + "]" + taskName;
        return toReturn;
    }
}
