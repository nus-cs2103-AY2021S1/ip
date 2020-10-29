package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command for Mrs Dino to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Index of the task in the todoList that needs to be deleted.
     */
    private int indexOfTaskToBeDeleted;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param indexOfTaskToBeDeleted index of the task in the todoList that needs to be deleted.
     */
    public DeleteCommand(int indexOfTaskToBeDeleted) {
        this.indexOfTaskToBeDeleted = indexOfTaskToBeDeleted;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task targetTask = taskList.get(indexOfTaskToBeDeleted - 1);
        taskList.remove(indexOfTaskToBeDeleted - 1);
        storage.saveTasks(taskList);
        int size = taskList.getSize();
        ui.printTaskDeleted(targetTask.toString(), size);
        String messageAfterExecution = deleteTaskToString(targetTask, size);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
