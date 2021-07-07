package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to list the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Lists the tasks in the TaskList object.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param notes   NoteList object containing the list of notes.
     * @param ui      Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user.
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        return ui.printList(tasks);
    }

    /**
     * Returns false to indicate that the Command does not exit the program.
     *
     * @return Exit program indicator
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
