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
    public String toSaveString() {
        return String.format("T @@ %s @@ %s", toSaveStringDone(), desc);
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s);
    }
}
