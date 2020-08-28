package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        // display add task success message
        String message = "Got it. I've added this task:";
        message += "\n\t\t".concat(task.toString());
        message += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        ui.printResponse(message);
        // update task data in the file
        storage.writeFile(taskList);
    }
}
