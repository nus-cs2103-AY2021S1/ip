package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommand;
import duke.tasks.Task;

/**
 * Represents a command to add task into task list.
 *
 */

public class AddCommand extends Command {
    private Task newTask;

    /**
     * Creates command with task given.
     *
     * @param newTask New Task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Executes main logic to add task.
     * Updates both current list and storage.
     * Displays item successfully added message to user.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     * @return @return UI message after executing add command.
     */
    public String execute (Ui ui, Storage listStorage, TaskList taskList) throws InvalidCommand {
        taskList.add(this.newTask);
        listStorage.addTask(this.newTask);
        return ui.addTask(this.newTask, taskList);
    }
}
