package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a Command which creates a new Event task to add to the task
 * list.
 */
public class EventCommand extends Command {

    private String description;
    private String time;

    /**
     * Creates an event command
     * @param description The task details.
     * @param time The event date.
     */
    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        try {
            Event event = new Event(description, time);
            tasks.addEvent(event);
            return ui.printAddStatements(event.toString(), lib.size());
        } catch (DukeException ex) {
            return ui.printExceptions(ex);
        }

    }

}
