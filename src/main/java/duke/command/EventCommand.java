package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
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
     * @throws InvalidTaskIdException
     * @throws EmptyDescriptionException
     * @throws InvalidFormatException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException {
        String[] details = Parser.parseEvent(input);
        String title = details[0];
        String at = details[1];
        Task task = new Event(title, at);
        taskList.add(task);
        ui.add(task);
    }
}
