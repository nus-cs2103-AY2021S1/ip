package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Event;

/**
 * Encapsulates an event command to be executed by Duke.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     * Creates a event task to be added to both the TaskList and Storage.
     * @param description Description of the event.
     * @param at Date of the event.
     */
    public EventCommand(String description, LocalDateTime at) {
        super();
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(new Event(description, at));
        storage.saveTasks(taskList.getTask());
    }
}
