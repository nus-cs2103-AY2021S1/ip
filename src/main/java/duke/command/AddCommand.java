package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * This class adds a to do task, deadline task
 * or an event to the task list whenever execute
 * is called.
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds task to the user tasklist and stores the new task list
     * in the storage.
     *
     * @param userTasks list of tasks from user.
     * @param storage user's storage.
     * @return response after command is executed.
     */
    public String execute(TaskList userTasks, Storage storage) {
        userTasks.addTask(task);
        storage.saveToFile(userTasks.getTaskList());
        response = new Ui().taskAddedMessage(task, userTasks.getTaskListSize());
        return getResponse();
    }
}