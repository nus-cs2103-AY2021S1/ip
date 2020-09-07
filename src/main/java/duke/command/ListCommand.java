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
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder sb = new StringBuilder();
        sb.append(ui.print("Here are the tasks in your list:\n"));
        List<Task> store = taskList.getList();
        store.forEach(task -> sb.append(ui.print(String.format("%d. %s\n", store.indexOf(task) + 1, task))));
        return sb.toString();
    }
}
