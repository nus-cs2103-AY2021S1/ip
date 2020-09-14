package commands;

import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Marks a specific task in the current task list of model.Duke as done.
 */
public class DoneCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private String userInput;

    /**
     * Constructs a done command.
     * @param taskList of model.Duke.
     * @param storage of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of command.
     */
    public DoneCommand(TaskList taskList, Storage storage, String userInput, Ui ui) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }

    @Override
    public String execute() throws DukeInvalidUserInputException {
        //Get number after done keyword
        if (this.userInput.length() == 4) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                    + "description of a done must not be empty.");
        }
        try {
            String intSubstring = this.userInput.substring(5);
            int indexNumber = Integer.parseInt(intSubstring);
            Task taskDone = this.taskList.markDone(indexNumber);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
            return this.ui.showMarkDone(taskDone);
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("My sincere apologies, but please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Oh dear, it appears that item does not exist.");
        }
    }

    @Override
    public String toString() {
        return "DoneCommand";
    }
}
