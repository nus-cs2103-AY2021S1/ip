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

    /**
     * Creates TodoTask with array as input. Array size should be exactly 1. First element will be used as description.
     *
     * @param arguments The input array.
     * @return A ToDoTask.
     */
    public static ToDoTask of(String... arguments) {
        assert (arguments.length == 1);
        return new ToDoTask(arguments[0]);
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s);
    }
}
