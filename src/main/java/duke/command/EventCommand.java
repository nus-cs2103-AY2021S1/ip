package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.Storage;
import duke.TaskList;

import java.time.LocalDateTime;

/**
 * EventCommand is an extension of a command.
 * It creates a event task to be added to both the TaskList and Storage.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime at;
    
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
