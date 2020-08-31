package duke.command;

import java.util.List;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Prints all the Tasks in the TaskList.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Here are the tasks in your list:");
        List<Task> store = taskList.getList();
        int count = 0;
        for (Task task : store) {
            count++;
            ui.print(String.format("%d. %s", count, task));
        }
    }
}
