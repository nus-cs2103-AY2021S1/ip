package duke.task;

/**
 * Todo class for Tasks that need to be completed.
 */
public class Todo extends Task {
    public static final String SYMBOL = "T";
    public static final String FORMAT = "todo [^ ]+.*";

    /**
     * Constructor for Todo class.
     *
     * @param title Title of task.
     */
    public Todo(String title, String[] tags) {
        super(title, tags);
    }

    /**
     * Overloads constructor for Todo class. Only used to read data from .txt file.
     *
     * @param input      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     */
    public Todo(String input, boolean isComplete, String[] tags) {
        super(input, isComplete, tags);
    }
}
