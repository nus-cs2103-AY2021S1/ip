package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents an adding {@link duke.task.Task} command.
 *
 * @author Tee Kok Siang
 */
public class AddCommand extends Command {
    /** Minimum number of word count for a AddCommand */
    public static final int MIN_WORD_COUNT = 2;
    /**
     * Constructs an AddCommand object.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes an AddCommand to add a task.
     * Adds task to taskList and saves it in the hard disk.
     * Displays feedback message.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     * @return Formatted response message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        // display add task success message
        String message = "Got it. I've added this task:";
        message += "\n\t\t".concat(task.toString());
        message += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        ui.printResponse(message);
        // update task data in the file
        storage.writeFile(taskList);
        return message;
    }
}
