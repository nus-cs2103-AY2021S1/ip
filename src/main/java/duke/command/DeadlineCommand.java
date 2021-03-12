package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.Deadline;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to add tasks with a deadline.
 */
public class DeadlineCommand extends Command {

    /**
     * The Deadline task to be stored.
     */
    private final Deadline deadline;

    /**
     * Constructs a command that adds a deadline task.
     *
     * @param description The description of the task.
     * @param dateTime The deadline of the task.
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        deadline = new Deadline(description, dateTime);
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        manager.addTask(deadline);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(deadline, manager.getTasks().size());
    }
}
