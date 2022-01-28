package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

/**
 * Represent an executable command.
 */
public interface Command {
    String execute(TaskList taskList, Ui ui, String input) throws
            InvalidTaskIdException, EmptyDescriptionException,
            InvalidFormatException, DuplicateException;
}
