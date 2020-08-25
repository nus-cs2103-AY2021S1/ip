package duke.command;

import duke.storage.Storage;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Is responsible for handling commands starting with <code>event</code>.
 */
public class EventCommand extends TaskCreationCommand {
    public final static String COMMAND = "event";
    public final static String TIME_SPECIFIER = "/at";
    
    private String description;
    private String time;

    /**
     * Creates an <code>EventCommand</code> object.
     * @param description The description of the task
     * @param time The time by which the task happens
     */
    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Creates a new <code>Event</code> task and add pass it to <code>TaskCreationCommand</code>'s execute method
     * to add the task to the <code>TaskList</code>.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, time);
        super.execute(task, ui, tasks);
    }
}
