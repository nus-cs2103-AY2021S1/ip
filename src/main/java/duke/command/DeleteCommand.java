package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.deleteTask(taskNumber);
        ui.print("Noted. I've removed this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list", taskList.numberOfTasks()));

    }

}
