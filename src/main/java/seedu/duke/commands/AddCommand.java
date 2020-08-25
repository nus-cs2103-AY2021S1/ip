package main.java.seedu.duke.commands;

import main.java.seedu.duke.DukeException;
import main.java.seedu.duke.todo.Task;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;
import main.java.seedu.duke.Storage;

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
