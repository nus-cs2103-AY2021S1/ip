package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the delete command where user deletes a particular task.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Instantiates a DeleteCommand with the index of task to be deleted.
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by deleting task from taskList, updating in file and displaying
     * the success message.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage updating on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(index);
            storage.saveData(taskList);
            return ui.deleteSuccess(task, taskList.getCount());
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
