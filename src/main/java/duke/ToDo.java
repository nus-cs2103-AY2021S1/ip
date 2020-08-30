package duke;

/**
 * Inherits from the Task class. Represents a to do task.
 * ToDo object stores task description and done status. A ToDo object is marked as not done by default.
 * A ToDo object is able to be set as done by calling setDone() method from the parent class.
 */
public class ToDo extends Task {
    /**
     * A ToDo object uses its parent class constructor
     * @param description description of to do task
     */
    public ToDo (String description) {
        super(description);
    }

    /**
     * Returns ToDo object as a string.
     * This method takes no parameters and returns the ToDo object as a string
     * in the form "[T][<Done Status>] <ToDo Description>".
     * This method overrides the method from parent class.
     * @return String this returns the ToDo object as a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns ToDo object as a string.
     * This method takes no parameters and returns the ToDo object as a string format
     * suitable for being parsed into a ToDo object.
     * String is in the form "[T][<Done Status>] <ToDo Description>".
     * This method overrides the method from parent class.
     * @return String this returns the ToDo object as a string
     */
    @Override
    public String toStringFileFormat() {
        return "[T]" + super.toStringFileFormat();
    }
}
