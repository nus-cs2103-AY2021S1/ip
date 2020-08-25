package main.java.seedu.duke.commands;

import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;
import main.java.seedu.duke.Storage;
import main.java.seedu.duke.DukeException;

/**
 * Represents the command to list out all the task.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the command to list out tasks.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     * @throws DukeException If the task list is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
