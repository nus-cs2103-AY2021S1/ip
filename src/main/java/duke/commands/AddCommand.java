package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.todo.Task;

/**
 * Represents the command to add task.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructor for AddCommand.
     * @param newTask The new task to be added.
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.addTask(newTask);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
