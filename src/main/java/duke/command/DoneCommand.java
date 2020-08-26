package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the command, marking a task as done in the provided TaskList.
     *
     * @param tasks TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException if the task cannot be found.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doTask(taskNo);
        ui.showPrompt("Nice! I've marked this task as done:\n  " + task);
    }
}
