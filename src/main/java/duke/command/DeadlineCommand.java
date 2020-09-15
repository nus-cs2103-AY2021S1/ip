package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to create a new Deadline Task.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final String description;
    private final String deadline;

    /**
     * Constructor for DeadlineCommand.
     * @param description Description of Deadline Task.
     * @param deadline Date for which Deadline is due.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks,
                        Queue<Action> commandQueue) throws DukeException {
        Task t = Deadline.createDeadline(description, deadline);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
