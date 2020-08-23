package main.java;

public class Todo extends Task {
    public Todo(String taskName) throws DukeInvalidTaskException{
        super(taskName);
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        return "[T]" + "[" + finished + "] " + taskName;
    }
}
