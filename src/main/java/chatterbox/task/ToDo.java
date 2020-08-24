package chatterbox.task;

/**
 * Task that has no specific timing.
 */
public class ToDo extends Task {
    /**
     * Stores the original user input including the command word, then sets task content.
     *
     * @param contents  User input without the command word.
     */
    public ToDo(String contents) {
        inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
        setContents(contents);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
