package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    /** The index of the task to be marked as done. */
    private final int taskNum;

    /**
     * Creates a new done command to mark a task with the specified index as done.
     *
     * @param taskNum is the index of the task to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks a task from the task list as done.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the done command.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskNum);
            Task doneTask = tasks.getTask(taskNum);
            storage.save(tasks.getTasks());
            return new CommandResponse(Ui.printDoneTask(doneTask), this.isExit());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("SORRY! The task number is not valid.");
        }
    }
}
