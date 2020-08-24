package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;

import java.io.IOException;

public class DoneCommand implements Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(index);
            storage.save(tasks);
            ui.showMarkDone(tasks.get(index));
        } catch (DukeInvalidIndexException | IOException e) {
            ui.showError(e);
        }
        return true;
    }
}
