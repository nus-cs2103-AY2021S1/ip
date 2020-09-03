package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add an Event task to the list.
 */
public class EventCommand extends Command {
    /**
     * A string array that represents the instructions of this command.
     */
    private String[] nextCommandArr;
    public EventCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Adds an Event task to the taskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @return A string indicating the event task added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.nextCommandArr.length < 2) {
            return new DukeException("The description of an event cannot be empty~").toString();
        } else {
            try {
                String[] eventArr = this.nextCommandArr[1].split("/at");
                Event newEvent = new Event(eventArr[0], eventArr[1].strip());
                tasks.add(newEvent);
                return ui.addTaskText(newEvent, tasks);
            } catch (Exception e) {
                return new DukeException("Please input a proper date for your event~").toString();
            }
        }
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}
