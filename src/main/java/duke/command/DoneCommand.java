
package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command to mark the task as done.
 */
public class DoneCommand implements Command {
    private int taskRank;
    /**
     * Return new Command that will delete an Event.
     * @param taskRank Index of the task to be marked done.
     * @throws DukeException when there is error creating the command
     */
    public DoneCommand(int taskRank) {
        this.taskRank = taskRank;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        tasks.doTask(this.taskRank);
        return Ui.showTaskDone(tasks.getTaskStatus(this.taskRank));
    }
}