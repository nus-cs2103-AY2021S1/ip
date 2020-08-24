package main.java;

/**
 * Holds the Todo object and relative operations.
 * Inherits from Task class.
 */
public class Todo extends Task {

    /**
     * Initializes the todo object.
     * Sets status to false(not done).
     * @param content Content of the todo object.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Initializes the todo object.
     * @param status Status of the todo object
     * @param content Content of the todo object.
     */
    public Todo(boolean status, String content) {
        super(status, content);
    }

    /**
     * Gives the string representation of the todo object.
     * @return String representation of the todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "  <-";
    }
}
