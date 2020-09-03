package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.exception.DukeException;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = new Deadline(description, deadline);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
