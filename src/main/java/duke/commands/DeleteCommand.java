package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes the specified task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a Command object for task deletion.
     *
     * @param command is the input given from the user.
     * @param index is the index of the task to be deleted.
     */
    public DeleteCommand(String command, String index) {
        super(command);
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task selectedTask = taskList.deleteTask(index);
            ui.printDeleteMessage(taskList, selectedTask);
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
        }
    }

}
