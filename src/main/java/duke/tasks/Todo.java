package main.java.duke.tasks;

/**
 * Represents tasks without any date/time attached to it.
 */
public class Todo extends Task {
    private char type = 'T';

    /**
     * Creates a Todo instance.
     *
     * @param task A string containing task details.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", type, super.toString());
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s", type, super.saveToString());
    }
}
