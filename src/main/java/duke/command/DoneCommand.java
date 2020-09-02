package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that will mark a task as done.
 */
public class DoneCommand extends Command {
    private final int taskNum;

    /**
     * Creates a new Done Command with the specified number of the task to be marked as done.
     *
     * @param taskNum the number of the tasked to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task in the task list as done.
     *
     * @param tasks the task list that stores the task to be marked as done.
     * @param ui the ui that will display a message when the task has been successfully marked as done.
     * @param storage the storage where the tasks are saved after marking.
     * @throws DukeException if the user enters an invalid task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskNum);
            ui.showDone(tasks.getTask(taskNum));
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Uh-oh! Looks like you have entered an invalid task number.");
        }
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
