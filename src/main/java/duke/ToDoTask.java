package duke;

/**
 * Represents a to-do task.
 */
public class ToDoTask extends Task {

    public ToDoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSavedString() {
        return String.format("T @@ %s @@ %s", toSavedStringIsDone(), desc);
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s);
    }
}
