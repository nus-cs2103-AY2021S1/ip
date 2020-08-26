package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Event;

/**
 * Represents a user command to create a new <code>Event</code>.
 */
public class EventCommand extends ComplexCommand {

    /**
     * Creates a new <code>EventCommand</code> with the given parameters.
     *
     * @param params Parameters.
     */
    public EventCommand(String params) {
        super(params);
    }

    /**
     * Creates a new <code>Event</code> and stores it to <code>taskManager</code>.
     * Displays an error message without terminating software loop if parameters are invalid.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to parse parameters and create a new Event
            String[] splitParams = this.parseParams();
            Event newEvent = new Event(splitParams[0], splitParams[1]);

            // Add new event to taskManager
            taskManager.storeTask(newEvent);

            ui.displayAfterAddTask(newEvent, taskManager.size());

        } catch (DukeInputException e) {
            ui.displayException(e);
        }

    }

    private String[] parseParams() throws DukeInputException {

        // Check if params is empty.
        if (this.params.equals("")) {
            throw new DukeInputException("'event' requires parameters.\n"
                    + "Use case: event <name> /at <when>");
        }

        String[] splitParams = this.params.split("/at ", 2);

        // Check if second parameter exists.
        if (splitParams.length != 2) {
            throw new DukeInputException("<" + this.params + "> is not valid for the 'event' command.\n"
                    + "Please add a /at timing to the task.");
        }

        return splitParams;

    }

}
