package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListNoteCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of existing notes.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing notes.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listNotes(tasks);
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
