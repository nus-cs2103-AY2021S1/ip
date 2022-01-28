package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents an event command.
 */
public class EventCommand implements Command {

    /**
     * Parses the input to extract the details: title and at.
     * Creates the Event object.
     * Adds it to the user data.
     * Notifies user that it has been executed.
     *
     * @param taskList
     * @param ui
     * @param input
     * @throws EmptyDescriptionException
     * @throws InvalidFormatException
     * @return event added message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, String input) throws EmptyDescriptionException, InvalidFormatException, DuplicateException {
        String[] details = Parser.parseEvent(input);
        assert details.length >= 2 : "Missing details in Event input";
        String title = details[0];
        String at = details[1];
        Task task = new Event(title, at);
        taskList.add(task);
        return ui.add(task);
    }
}
