package duke.task;

/**
 * Represents a Todo task.
 * @author Tee Kok Siang
 */
public class Todo extends Task {
    /** Keyword for reading and writing from/to a file */
    public static final String TODO_KEYWORD = "T";

    /**
     * Constructs a Todo object.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description, TODO_KEYWORD);
    }
}
