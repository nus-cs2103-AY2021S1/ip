package duke.command;

import duke.*;
import duke.exceptions.DukeException;
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
     * Create a Event with the user entered description and date String, store it in TaskList,
     * print feedback to user and store the new Deadline in Storage.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     * @throws WrongDateFormatException if invalid date String provided
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WrongDateFormatException {
        LocalDateTime eventDateTime = DateParser.parseString(dateStr);
        Event event = new Event(description, eventDateTime);
        tasks.addTask(event);
        ui.printMessage(String.format("Okay, I've added the following event: \n %s", event.toString()));
        storage.updateTasks(tasks.getListOfTasks());
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
