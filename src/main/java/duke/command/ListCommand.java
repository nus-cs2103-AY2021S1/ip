package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

/**
 * Represents a list task command.
 * @author Tee Kok Siang
 */
public class ListCommand extends Command{
    /**
     * Executes a ListCommand to list all the tasks.
     * Display the tasks.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            ui.printResponse("No task added yet...");
            return;
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("\n\t%d.%s", (i + 1), tasks.get(i));
            taskMessage.append(task);
        }
        ui.printResponse(taskMessage.toString());
    }
}
