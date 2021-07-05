package commands;

import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Deletes a specific task in the current task list of model.Duke.
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private String userInput;

    /**
     * Constructs a delete command.
     * @param taskList of model.Duke.
     * @param storage of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of command.
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }

    @Override
    public String execute() throws DukeInvalidUserInputException {
        //Get number after done keyword
        String intSubstring = userInput.substring(7);
        try {
            int indexNumber = Integer.parseInt(intSubstring);
            Task deletedTask = this.taskList.delete(indexNumber);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
            return this.ui.showDelete(deletedTask)
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
