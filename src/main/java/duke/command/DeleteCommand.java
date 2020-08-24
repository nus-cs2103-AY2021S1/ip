package duke.command;

import duke.Ui;
import duke.Storage;
import duke.ActionType;
import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int taskIndex;
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.getList().size()) {
            throw new DukeException("duke.tasks.Task does not exist _(´ཀ`」 ∠)_");
        } else {
            Task deletedTask = tasks.delete(taskIndex);
            storage.updateFile(tasks);
            ui.printTask(deletedTask, ActionType.MARK_DONE);
        }
    }
}
