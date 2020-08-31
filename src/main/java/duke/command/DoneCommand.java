package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int taskNum) {
        index = taskNum - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(index);
            ui.showDone(tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Uh-oh! Looks like you have entered an invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
