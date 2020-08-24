package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand sets the task specified in the command as done in the task list
 */
public class DoneCommand extends UpdateCommand {
    public DoneCommand(String commandString) {
        super(CommandType.DONE, commandString);
    }

    /**
     * Sets the task specified in the command as done in the task list
     *
     * @param tasks task list of tasks
     * @throws DukeException when the index in the command string is invalid
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        int index = super.getTaskTargetIndex();
        Task task = tasks.get(index);
        task.setDone(true);
        Ui.showDone(task);
    }
}
