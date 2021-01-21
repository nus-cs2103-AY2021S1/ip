package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command where user completes a task.
 */
public class DoneCommand extends Command {

    private final int index;

    /**
     * Instantiates a DoneCommand with the index of task that is completed.
     * @param index the index of task that is completed
     */
    public DoneCommand(int index) {
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
     * Executes the command by completing the task from taskList, updating in file and displaying
     * the success message.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage updating on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.completeTask(index);
            storage.saveData(taskList);
            return ui.completeSuccess(task);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
