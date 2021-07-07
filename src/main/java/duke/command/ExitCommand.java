package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program for the user. Returns a String response.
     *
     *  @param tasks   TaskList object containing the list of tasks.
     * @param notes   NoteList object containing the list of notes.
     * @param ui      Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user.
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        String output = ui.printExit();
        return output;
    }

    /**
     * Returns true to indicate that the Command exits the program.
     *
     * @return Exit program indicator.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
