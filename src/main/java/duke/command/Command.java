package duke.command;

import duke.exception.DukeException;
import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command. Consists of an execute method where the command manipulates
 * the TaskList object, Ui object and Storage object to achieve the desired outcome. Each
 * Command will extend from this abstract class and have their own execute and isExit method.
 */
public abstract class Command {

    /**
     * Returns a String representation of the response to that command.
     *
     * @param tasks TaskList object containing the list of tasks.
     * @param notes NoteList object containing the list of notes.
     * @param ui Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user.
     * @throws DukeException When user request is invalid for that command.
     */
    public abstract String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage)
            throws DukeException;

    /**
     * Returns a boolean indicator for the Command to exit the program.
     *
     * @return Boolean indicator.
     */
    public abstract boolean isExit();
}
