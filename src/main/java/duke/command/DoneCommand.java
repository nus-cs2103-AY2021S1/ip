
package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private int taskRank;

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