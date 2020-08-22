package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.EventTask;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Command to add a new
 * EventTask.
 */
public class EventCommand implements Command {
    private final String description;
    private final String timePeriod;

    /**
     * Initializes the EventCommand that
     * will add a new EventTask.
     * @param description The description of the EventTask.
     * @param timePeriod The timePeriod when the EventTask will
     *                   occur.
     */
    public EventCommand(String description, String timePeriod) {
        this.description = description;
        this.timePeriod = timePeriod;
    }

    /**
     * Adds a new EventTask to the taskList.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     * @throws DukeException If timePeriod format is wrong.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task eventTask = new EventTask(description, timePeriod);
        tasks.add(eventTask);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "Sure! I have added the following event task to your list: ",
                eventTask.toString(),
                tasks.getListStatus())), false, false);
    }
}