package duke.command;

import duke.ActionType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class MarkDoneCommand extends Command {
    private final int taskIndex;
    public MarkDoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("duke.tasks.Task does not exist _(´ཀ`」 ∠)_");
        } else {
        Task completedTask = tasks.markDone(taskIndex);
        storage.updateFile(tasks);
        ui.printTask(completedTask, ActionType.MARK_DONE);
        }
    }
}
