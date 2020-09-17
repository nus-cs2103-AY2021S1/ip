package duke;

/**
 * TODO class extends Task class to represent a task that does not have a specific time.
 */
public class Todo extends Task {

    /**
     * Constructor
     * @param name Name of the todo task.
     * @param status Completion status of the task
     */
    public Todo(String name, Status status) {
        super(name, status);
    }

    /**
     * Generates a String that represents the object.
     * This String is used for printing to user as well as to be stored in the data management file.
     * @return A String.
     */
    @Override public String toString() {
        return "[T] " + this.status.statusToSymbol() + this.name;
    }
}