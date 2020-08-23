package duke.exceptions;

public class TaskListTranslatorException extends DukeException {

    public TaskListTranslatorException() {
        super("OOPS! Error reading from file.");
    }
}
