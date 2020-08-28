package duke.command;

import duke.task.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline command to be executed by Duke.
 * Creates a deadline task to be added to both the TaskList and Storage.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;
    
    public DeadlineCommand(String description, LocalDateTime by) {
        super();
        this.description = description;
        this.by = by;
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(new Deadline(description, by));
        storage.saveTasks(taskList.getTask());
    }
}
