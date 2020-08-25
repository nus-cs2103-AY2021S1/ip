package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int size) {
        this.index = size;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.listSize()) {
            tasks.taskDone(index, ui);
            storage.Save(tasks.convertToFile());
        } else {
            throw new DukeException("    Invalid index entry for done command.");
        }
    }
}
