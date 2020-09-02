package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    private static int taskindex;

    /**
     * Constructor for a new DoneCommand object
     *
     * @param taskindex index of task in task list to be mark as done
     */
    public DoneCommand(int taskindex) {
        DoneCommand.taskindex = taskindex - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskindex);
            Ui.display("Nice! I've marked this task as done:\n   "
                    + tasks.getTask(taskindex));
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new DukeException("This task does not exist!");
        }
    }
}
