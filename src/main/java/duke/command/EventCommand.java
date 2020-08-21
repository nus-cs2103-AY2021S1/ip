package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represent a "Event" Command
 * A <code>EventCommand</code> object that corresponds to a command of an input "event"
 * and contains a description and date as a String
 */
public class EventCommand extends Command {

    String description, date;

    /**
     * Constructor of the Event Class
     *
     * @param description description of the task
     * @param date date of the task
     */
    public EventCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Creates a new Task based on the description and date.
     * Adds the task into the taskList object and prints the corresponding messages.
     * Save the task into the datafile.
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Event(description, date);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
