package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.WrongItemIndexException;

import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Command that marks a task from the user's list as completed("done") when executed.
 */
public class DoneCommand extends Command {
    /** A string representation of the number corresponding to the task to be marked */
    private final String description;

    /**
     * Public constructor.
     * @param description String representation of the number
     *                    corresponding to the task to be marked.
     */
    public DoneCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(this.description);
            Task task = tasks.getTask(taskNum);
            tasks.markDone(taskNum, storage);

            System.out.println("Can, I help you mark this as done liao:" +
                    "\n  " +
                    task.toString());
        } catch (NumberFormatException e) {
            throw new WrongItemIndexException(CommandType.DONE.toString().toLowerCase(),
                    tasks.getListLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
