package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.tasks.EventTask;
import duke.tasks.Task;

/**
 * Represents the Command to add a new EventTask.
 */
public class EventCommand implements Command {

    private final String description;
    private final String timePeriod;

    /**
     * Initializes the EventCommand that will add a new EventTask.
     *
     * @param description The description of the EventTask.
     * @param timePeriod  The timePeriod when the EventTask will occur.
     */
    public EventCommand(String description, String timePeriod) {
        this.description = description;
        this.timePeriod = timePeriod;
    }

    /**
     * Adds a new EventTask to the taskList.
     *
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The response to the user indicating the event task has been added to the taskList.
     * @throws DukeException If timePeriod format is wrong.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        Task eventTask = new EventTask(description, timePeriod);
        tasks.add(eventTask);
        return "Sure! I have added the following event task to your list:\n" + eventTask.toString() + "\n"
                + tasks.getListStatus();
    }
}