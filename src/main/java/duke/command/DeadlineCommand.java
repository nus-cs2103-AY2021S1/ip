package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

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
     * @param description the description of the task
     * @param date the deadline of the task
     */
    public DeadlineCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, date);
        manager.addTask(task);
        ui.showAddMessage(task, manager.getTasks().size());
        storage.saveTasks(manager.getTasks());
    }
    
}