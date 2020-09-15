package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class SaveCommand extends Command {

    /**
     * Saves the TaskList into a save file and ends the Ui.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
            ui.end();
            return "Your list has been saved!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SaveCommand;
    }
}
