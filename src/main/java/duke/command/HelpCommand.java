package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to list the tasks in the TaskList.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        return ui.printHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
