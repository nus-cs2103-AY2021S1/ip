package command;

import exception.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private String eventDetails;

    public EventCommand(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    /**
     * Creates new event with date and time, adds event to TaskList then updates the Storage.
     *
     * @param taskList the list of tasks.
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] details = this.eventDetails.split(" /at ", 2);
            Event newEvent = new Event(details[0], details[1], false);
            taskList.add(newEvent);
            String output = ui.LINE + "Got it. I've added this task: \n"
                    + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list."
                    + "\n" + ui.LINE;
            System.out.println(output);
            storage.save(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please specify your event description and details! \n" + ui.LINE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
