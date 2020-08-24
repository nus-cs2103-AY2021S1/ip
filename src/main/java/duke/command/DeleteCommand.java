package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Removes a Task from the TaskList.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.deleteTask(taskNumber);
        ui.print("Noted. I've removed this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list", taskList.getNumberOfTasks()));

    }

}
