package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;

/**
 * Encapsulates a deadline command to be executed by Duke.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Creates a deadline task to be added to both the TaskList and Storage.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
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
