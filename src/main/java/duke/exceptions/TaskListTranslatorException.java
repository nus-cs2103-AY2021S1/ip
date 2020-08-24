package duke.exceptions;

/** Thrown to indicate that there is a problem translating the file contents to a TaskList. */
public class TaskListTranslatorException extends DukeException {

    /** Constructs a TaskListTranslatorException with the relevant detail message. */
    public TaskListTranslatorException() {
        super("OOPS! Error reading from file.");
    }
}
