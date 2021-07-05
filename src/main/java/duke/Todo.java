package duke;

/**
 * Represents a to-do task containing a description.
 * Inherits from abstract class Task.
 */
public class Todo extends Task {

    /**
     * Instantiates a new to-do task with input as description.
     *
     * @param description concise info of to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    @Override
    public String getTxtFormat() {
        return "todo, " + super.getTxtFormat();
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
