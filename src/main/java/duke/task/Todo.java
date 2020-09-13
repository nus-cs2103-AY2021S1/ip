package duke.task;

/** Represents a todo task */
public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Todo complete() {
        return new Todo(this.name, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatTask() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "T | " + isDoneSignal + " | " + this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String taskString = super.toString();
        return "[T]" + taskString;
    }
}
