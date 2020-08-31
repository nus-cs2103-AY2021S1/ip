package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to add tasks with a deadline.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * A local date instance to store the task deadline.
     */
    private final LocalDate date;

    /**
     * Constructs a command that adds a deadline task.
     *
     * @param description The description of the task.
     * @param date The deadline of the task.
     */
    public DeadlineCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, date);
        manager.addTask(task);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(task, manager.getTasks().size());
    }
}
