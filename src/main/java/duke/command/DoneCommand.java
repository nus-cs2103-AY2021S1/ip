package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.doneTask(taskNumber);
        ui.print("Nice! I've marked this task as done:");
        ui.print(task.toString());
    }
}
