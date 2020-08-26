package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    /**
     * Saves the TaskList into a save file and ends the Ui.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
            ui.end();
        } catch (DukeException e) {
            ui.say(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
