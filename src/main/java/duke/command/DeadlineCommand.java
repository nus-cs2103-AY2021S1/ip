package duke.command;

import duke.action.Action;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.exception.DukeException;

import java.util.Queue;

/**
 * Represents a call to create a new Deadline Task.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String deadline;

    public static final String COMMAND_WORD = "deadline";

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
    public void execute(Ui ui, Storage storage, TaskList tasks, Queue<Action> commandQueue) throws DukeException {
        Task t = Deadline.createDeadline(description, deadline);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
