package duke.fxcommand;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents the command to add a new DeadlineTask.
 */
public class DeadlineCommand implements Command {

    private final String description;
    private final String deadline;

    /**
     * Initializes a DeadlineCommand.
     *
     * @param description The description of the DeadlineTask.
     * @param deadline    The deadline of the DeadlineTask.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Adds a new DeadlineTask to the taskList.
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @throws DukeException If deadline format is wrong.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task deadlineTask = new DeadlineTask(description, deadline);
        tasks.add(deadlineTask);
        return "Sure! I have added the following deadline task to your list:\n" + deadlineTask.toString() + "\n" + tasks.getListStatus();
    }
}
