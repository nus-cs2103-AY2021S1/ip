package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Encapsulates a command for Mrs Dino to mark a task as done.
 */
public class DoneCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Index of the task in the todoList that needs to be marked as done.
     */
    private int indexOfTaskToBeDone;

    /**
     * Constructs a new DoneCommand.
     *
     * @param indexOfTaskToBeDone Index of the task in the todoList that needs to be marked as done.
     */
    public DoneCommand(int indexOfTaskToBeDone) {
        this.indexOfTaskToBeDone = indexOfTaskToBeDone;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task targetTask = taskList.get(indexOfTaskToBeDone - 1);
        String message = targetTask.completeTask();
        storage.saveTasks(taskList);
        ui.printDone(message);
        return new CommandResult(message);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
