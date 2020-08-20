package duke.task;

/** Represents a todo task */
public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public Todo complete() {
        return new Todo(this.getName(), true);
    }

    @Override
    public String format() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "T | " + isDoneSignal + " | " + name;
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        return "[T]" + taskString;
    }
}
