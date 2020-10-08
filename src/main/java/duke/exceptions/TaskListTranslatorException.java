package duke.exceptions;

import static duke.utils.Messages.MESSAGE_TASKLIST_TRANSLATOR_EXCEPTION;

/**
 * Thrown to indicate that there is a problem translating the file contents to a TaskList.
 */
public class TaskListTranslatorException extends DukeException {

    /**
     * Constructs a TaskListTranslatorException with the relevant detail message.
     */
    public TaskListTranslatorException() {
        super(MESSAGE_TASKLIST_TRANSLATOR_EXCEPTION);
    }
}
