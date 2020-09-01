package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNo;

    /**
     * Constructor of DoneCommand.
     * @param taskNo The task number of the task to be marked as done.
     */
    public DoneCommand(int taskNo) {
        super("done");
        this.taskNo = taskNo;
    }

    /**
     * Executes the command to mark a task as done.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.doneTask(taskNo);
        // ui.showDoneMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
