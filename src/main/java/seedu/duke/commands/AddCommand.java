package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.todo.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;

/**
 * Represents the command to add task.
 */
public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super("add");
        this.newTask = newTask;
    }

    /**
     * Executes the command to add task to task list.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
