package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to create new event task.
 */
public class EventCommand extends Command {
    private String eventDetails;

    public EventCommand(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    /**
     * Creates new event with date and time, adds event to TaskList then updates the Storage.
     * @param taskList the list of tasks.
     * @param ui
     * @param storage
     * @return the Duke response to show user
     * @throws DukeException when event task details are not specified.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] details = this.eventDetails.split(" /at ", 2);
            Event newEvent = new Event(details[0], details[1], false);
            taskList.add(newEvent);
            storage.save(taskList);
            String response = "Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please specify your event description and details!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
