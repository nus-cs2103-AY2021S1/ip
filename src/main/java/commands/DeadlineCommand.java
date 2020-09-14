package commands;

import data.exception.DukeIllegalCommandException;
import data.exception.DukeInvalidUserInputException;
import data.task.Deadline;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * Adds a Deadline task into the current task list of model.Duke.
 */
public class DeadlineCommand extends CreateTaskCommand {

    /**
     * Constructs a deadline command.
     * @param taskList of model.Duke.
     * @param storage of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of tasks.
     */
    public DeadlineCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(taskList, storage, ui, userInput);
    }

    @Override
    public String execute() throws DukeInvalidUserInputException, DukeIllegalCommandException {
        try {
            String userInputWithoutCommand = this.userInput.substring(this.userInput.indexOf(' '));
            String[] userInputWithoutCommandArr = userInputWithoutCommand.split("/");
            String description = userInputWithoutCommandArr[0].trim();
            checkDescription(description, "deadline");
            checkFollowUpCommand(userInputWithoutCommandArr, "/by");
            String followUpCommand = Parser.parseFollowUpCommand(userInputWithoutCommandArr[1]);
            if (followUpCommand.equals("by")) {
                checkDateTime(userInputWithoutCommandArr[1], "deadline");
                String dateTime = userInputWithoutCommandArr[1]
                        .substring(userInputWithoutCommandArr[1].indexOf(" ")).trim();
                Deadline newTask = new Deadline(description, dateTime);
                return addTask(newTask);
            } else {
                throw new DukeIllegalCommandException(followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. "
                    + "The format should be as follows YYYY-MM-DD hhmm.");
        }
    }

    @Override
    public String toString() {
        return "DeadlineCommand";
    }
}
