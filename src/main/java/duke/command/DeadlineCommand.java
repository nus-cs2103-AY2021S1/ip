package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;

/**
 * The DeadlineCommand class contains methods pertaining to the DeadlineCommand.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(deadline);
        ui.addTask(deadline, taskList);
        storage.addData(deadline.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
