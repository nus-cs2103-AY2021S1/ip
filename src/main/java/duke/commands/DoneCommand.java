package duke.commands;


import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.Ui;

public class DoneCommand extends Command {
    private int taskIndex;
    public DoneCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        Task currentTask = taskList.get(taskIndex - 1);
        currentTask.markAsDone();
        return Ui.doneTask(currentTask);
    }
}

