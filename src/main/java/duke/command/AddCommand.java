package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskType;

import java.time.LocalDateTime;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String description;
    private final LocalDateTime dateTime;

    public AddCommand(TaskType type, String description) {
        this(type, description, null);
    }

    public AddCommand(TaskType type, String description, LocalDateTime dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes the command, adding a task to the provided TaskList.
     *
     * @param tasks TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException if the task cannot be added.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.addTask(type, description, dateTime);
        ui.showPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + tasks.getTasks().size()
                + (tasks.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }
}
