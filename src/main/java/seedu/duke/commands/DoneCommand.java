package main.java.seedu.duke.commands;

import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.doneTask(taskNo);
        // ui.showDoneMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
