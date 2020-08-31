package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks the specified task to be done or not.
 */
public class UpdateCommand extends Command {
    private int index;

    /**
     * Creates an update command to mark the task at the specified index as done.
     *
     * @param command is the user input.
     * @param index is the index of the task in the list to be updated.
     */
    public UpdateCommand(String command, String index) {
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
            Task selectedTask = taskList.getTask(index);
            selectedTask.setDone();
            ui.printDoneMessage(selectedTask);
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
        }
    }

}
