package duke;

/**
 * Todo class for Tasks that need to be completed.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param input Title of task.
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * Overloads constructor for Todo class.
     *
     * @param input      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     */
    public Todo(String input, boolean isComplete) {
        super(input, isComplete);
    }
}