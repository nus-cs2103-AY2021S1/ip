package commands;

import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Deletes a specific task in the current task list of Duke.
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private String user_input;

    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, String user_input) {
        this.taskList = taskList;
        this.storage = storage;
        this.user_input = user_input;
        this.ui = ui;
    }

    @Override
    public String execute() throws DukeInvalidUserInputException {
        //Get number after done keyword
        String int_substring = user_input.substring(7);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task deletedTask = this.taskList.delete(int_substring_converted);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
            return this.ui.showDelete(deletedTask) + "\n"
                    + this.ui.showTotalTasks(this.taskList.getTotalTask());
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("My sincere apologies, but please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Oh dear, it appears that item does not exist.");
        }
    }

    @Override
    public String toString() {
        return "DeleteCommand";
    }
}
