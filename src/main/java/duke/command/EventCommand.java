package duke.command;

import java.io.IOException;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Adds a event task.
 */
public class EventCommand extends Command {
    private static String description;
    private static String at;

    /**
     * Constructor for a new EventCommand object
     *
     * @param description details about the Deadline
     * @param at          date the event is held at in yyyy-mm-dd format
     */
    public EventCommand(String description, String at) {
        EventCommand.description = description;
        EventCommand.at = at;
    }

    /**
     * Executes the command.
     *
     * @param tasks   list of tasks
     * @param ui      object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task temp = new Event(description, at);
        tasks.addTask(temp);
        Storage.appendToFile(temp);
        Ui.display("Yay! New task added:\n   "
                + temp
                + "\nNow you have "
                + tasks.getSize()
                + " tasks in your list.");
    }
}
