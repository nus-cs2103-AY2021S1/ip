package duke.command;

import duke.ui.Response;
import duke.ui.Ui;
import duke.Storage;
import duke.exceptions.WrongDateFormatException;
import duke.parser.DateParser;
import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * Command to create a Event Task. Created by using "event description /at DD MMM YYYY hhmm"
 */
public class EventCommand extends Command {

    private final String description;
    private final String dateStr;

    public EventCommand(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
    }

    /**
     * Creates a Event with the user entered description and datetime String, store it in TaskList,
     * formats a feedback String to be displayed to user and store the new Deadline in Storage.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @return Response object containing the feedback String to be displayed by the GUI
     * @throws WrongDateFormatException if invalid date String provided
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws WrongDateFormatException {
        LocalDateTime eventDateTime = DateParser.parseString(dateStr);
        Event event = new Event(description, eventDateTime);
        tasks.addTask(event);
        String message = ui.formatMessage(String.format("Okay, I've added the following event: \n %s",
                event.toString()));
        storage.updateTasks(tasks.getListOfTasks());
        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof EventCommand) {
            return this.description.equals(((EventCommand) other).description) &&
                    this.dateStr.equals(((EventCommand) other).dateStr);
        } else {
            return false;
        }
    }
}
