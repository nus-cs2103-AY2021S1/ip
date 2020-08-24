package duke;

public class Todo extends Task {

    public Todo(boolean isComplete, int index, String instructions) {
        super(isComplete, index, instructions);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[T][✓] " + this.instructions;
        } else {
            return "[T][✗] " + this.instructions;
        }
    }
}
