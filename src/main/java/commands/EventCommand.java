package commands;

import data.exception.DukeIllegalFollowUpCommandException;
import data.exception.DukeInvalidUserInputException;
import data.task.Event;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * Adds an Event task into the current task list of model.Duke.
 */
public class EventCommand extends CreateTaskCommand {

    /**
     * Constructs a event command.
     * @param taskList of model.Duke.
     * @param storage of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of tasks.
     */
    public EventCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(taskList, storage, ui, userInput);
    }

    @Override
    public String execute() throws DukeInvalidUserInputException, DukeIllegalFollowUpCommandException {
        try {
            String userInputWithoutCommand = this.userInput.substring(this.userInput.indexOf(' '));
            String[] userInputWithoutCommandArr = userInputWithoutCommand.split("/");
            String description = userInputWithoutCommandArr[0].trim();
            checkDescription(description, "event");
            checkFollowUpCommand(userInputWithoutCommandArr, "/at");
            String followUpCommand = Parser.parseFollowUpCommand(userInputWithoutCommandArr[1]);
            if (followUpCommand.equals("at")) {
                checkDateTime(userInputWithoutCommandArr[1], "event");
                String dateTime = userInputWithoutCommandArr[1]
                        .substring(userInputWithoutCommandArr[1].indexOf(" ")).trim();
                Event newTask = new Event(description, dateTime);
                return addTask(newTask);
            } else {
                throw new DukeIllegalFollowUpCommandException("event", followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                    + "description of an event must not be empty.");
        }
    }

    @Override
    public String toString() {
        return "EventCommand";
    }
}
