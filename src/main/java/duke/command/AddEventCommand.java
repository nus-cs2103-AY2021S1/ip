package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDateTimeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to add new Event.
 */
public class AddEventCommand extends Command {

    /** Description of Event to be added */
    private final String description;

    /** String representation of date and time of Event to be added */
    private final String at;

    /**
     * Constructs a <code>AddEventCommand</code> object.
     *
     * @param description Description of Event to be added.
     * @param at String representation of date and time of Event to be added.
     */
    public AddEventCommand(String description, String at) {
        super(false);
        this.description = description;
        this.at = at;
    }

    /**
     * Adds an Event to the TaskList and notify the user if successful.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @throws InvalidTaskDateTimeException If date and time format is invalid.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Event(description, at);
            tasks.addTask(task);
            storage.save(tasks);
            String responseMessage = "Got it. I've added this event: \n\t   "
                    + task + "\n\t "
                    + "Now you have "
                    + getTaskDescription(tasks.getNumberOfTask())
                    + " in the list.";
            boolean shouldExit = getIsExit();
            assert !shouldExit : "shouldExit should be false";
            return new CommandResponse(responseMessage, shouldExit);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeException();
        }
    }

    /**
     * Returns a String description of the number of Task.
     *
     * @param noOfTask Number of Task in TaskList
     * @return String description of the number of Task.
     */
    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddEventCommand) {
            AddEventCommand c = (AddEventCommand) obj;
            return c.description.equals(this.description)
                    && c.at.equals(this.at)
                    && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}
