package duke.commands;

import duke.support.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete certain task from task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a {@code DeleteCommand} with given task index.
     * @param taskIndex The index of the task deleted.
     */
    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        Task currentTask = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        return Ui.deleteTask(currentTask, taskList);
    }
}
