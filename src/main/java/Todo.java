package main.java;

/**
 * Todos are tasks without any date/time attached to it
 *
 * @author Lio
 */
public class Todo extends Task {
    /**
     * Constructor
     *
     * @param name Name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Converts the task to data form
     */
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
