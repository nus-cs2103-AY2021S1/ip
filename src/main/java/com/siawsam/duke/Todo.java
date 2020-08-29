package com.siawsam.duke;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance containing a description.
     *
     * @param description The todo item's description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo item.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
