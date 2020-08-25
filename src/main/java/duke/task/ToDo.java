package main.java.duke.task;

/**
 * Encapsulates a todo.
 */
public class ToDo extends Task {

    /**
     * Constructor
     * @param description Description of the todo
     * @param isComplete Completion status of the todo
     */
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete, null);
    }

    /**
     * Gets the string representation of the todo to be written into the file upon exit
     * @return String representation of the todo
     */
    @Override
    public String[] getDataString() {
        return new String[] {"todo", String.valueOf(isComplete), description};
    }

    /**
     * Gets the string representation of the todo to be printed in the UI
     * @return String representation of the todo
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
