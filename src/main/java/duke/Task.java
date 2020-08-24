package duke;

import java.io.Serializable;

public class Task implements Serializable {
    protected boolean isComplete;
    protected int index;
    protected String instructions;

    public Task(boolean isComplete, int index, String instructions) {
        this.isComplete = isComplete;
        this.index = index;
        this.instructions = instructions;
    }

    // mark a task as complete
    public Task markDone() {
        if (this instanceof Todo) {
            return new Todo(true, this.index, this.instructions);
        } else if (this instanceof  Deadline) {
            return new Deadline(true, this.index, this.instructions, ((Deadline) this).getDate());
        } else if (this instanceof  Event) {
            return new Event(true, this.index, this.instructions, ((Event) this).getTime());
        } else {
            return new Task(true, this.index, this.instructions);
        }
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[✓] " +  this.instructions;
        } else {
            return "[✗] " + this.instructions;
        }
    }
}
