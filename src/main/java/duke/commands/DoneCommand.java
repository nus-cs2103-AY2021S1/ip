package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private final int idx; // NEED TO ALL CAP THIS?

    /**
     * Initializes done command with given index
     * @param i The index of the task that has been completed
     */
    public DoneCommand(int i) {
        idx = i;
    }

    /**
     * Marks task as done and then displays result to user
     * Then writes the updated task list to computer
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            tasks.get(idx).setAsDone();
            storage.save(tasks);
            return ui.showDoneMessage(tasks.get(idx));
        } catch (IOException e) {
            throw new DukeException("cannot save data due to unexpected error lah.");
        }
    }
}
