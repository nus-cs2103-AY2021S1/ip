package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.Task;

public class DeleteCommand extends Command {
    private int taskIndex;
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
