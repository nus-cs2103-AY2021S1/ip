package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from tasklist.
     *
     * @param tasks tasklist object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return a string of delete task message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        Task.reduceOneTasks();
        String output = ui.displayDeleteMessage(task);
        tasks.deleteTask(index);
        return output;
    }

}
