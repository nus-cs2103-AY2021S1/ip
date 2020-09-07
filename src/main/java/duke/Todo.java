package duke;

/**
 * Todo class represents a Todo Task that stores instructions from user.
 */
public class Todo extends Task {

    public Todo(boolean isComplete, int index, String instructions) {
        super(isComplete, index, instructions);
    }

    public Todo(boolean isComplete, int index, String instructions, String tag) {
        super(isComplete, index, instructions, tag);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[T][✓] " + this.instructions + printTag();
        } else {
            return "[T][✗] " + this.instructions + printTag();
        }
    }
}
