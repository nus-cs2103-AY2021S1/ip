package commands;

import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Abstract extension of the Command class for commands that
 * deal with creating tasks. Contains several methods that such
 * commands have in common.
 */

public abstract class CreateTaskCommand extends Command {

    protected TaskList taskList;
    protected Storage storage;
    protected String userInput;

    CreateTaskCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }
    /**
     * Checks whether description is empty.
     * @param description to check.
     * @throws DukeInvalidUserInputException when there description is empty.
     */
    protected static void checkDescription(String description, String command) throws DukeInvalidUserInputException {
        if (description.isEmpty()) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                    + "description of an " + command + " must not be empty.");
        }
    }

    /**
     * Checks whether there is a follow up command in the String array.
     * @param withoutCommandArr to check.
     * @throws DukeInvalidUserInputException when String array does not contain a follow up command.
     */
    protected static void checkFollowUpCommand(String[] withoutCommandArr, String followUpCommand)
            throws DukeInvalidUserInputException {
        if (withoutCommandArr.length < 2) {
            throw new DukeInvalidUserInputException("It appears you are missing a "
                    + "follow up '" + followUpCommand + "' command.");
        }
    }

    /**
     * Checks whether date time input exists.
     * @param dateTime to check.
     * @throws DukeInvalidUserInputException when date and time is missing.
     */
    protected static void checkDateTime (String dateTime, String command)
            throws DukeInvalidUserInputException {
        if (!dateTime.trim().contains(" ")) {
            throw new DukeInvalidUserInputException("It appears you are missing the "
                    + "date and time for your " + command + ".");
        }
    }

    protected String addTask(Task newTask) {
        this.taskList.add(newTask);
        this.storage.saveTask(newTask);
        return this.ui.showAddedToList(newTask) + Ui.showBreakLine()
                + this.ui.showTotalTasks(this.taskList.getTotalTask());
    }
}
