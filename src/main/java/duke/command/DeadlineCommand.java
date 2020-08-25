package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;

/**
 * The DeadlineCommand class contains methods pertaining to the DeadlineCommand.
 *
 *  @author  Yen Pin Hsuan
 *  @version 1.0
 */
public class DeadlineCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            Deadline deadline = ui.getDeadline();
            taskList.addTask(deadline);
            ui.addTask(deadline, taskList);
            storage.addData(deadline.store());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
