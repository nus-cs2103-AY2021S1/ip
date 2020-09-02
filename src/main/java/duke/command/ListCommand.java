package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Represents a list task command.
 * @author Tee Kok Siang
 */
public class ListCommand extends Command {
    /**
     * Executes a ListCommand to list all the tasks.
     * Display the tasks.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     * @return Formatted response message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            ui.printResponse("No task added yet...");
            return "No task added yet...";
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the tasks in your list:");
        taskMessage.append(TaskList.getTaskMessage(tasks));
        ui.printResponse(taskMessage.toString());
        return taskMessage.toString();
    }
}
