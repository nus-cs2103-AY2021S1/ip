package main.command;

import main.task.Todo;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the add todo command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class AddTodoCommand implements Command {
    private final Todo todo;

    /**
     * Constructs an AddTodoCommand instance and the Todo object
     * with the description of the task.
     * @param description the description of the task.
     */
    public AddTodoCommand(String description) {
        todo = new Todo(description);
    }

    /**
     * Adds the Todo object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(todo);
        ui.printAddSuccess(todo, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddTodoCommand) {
            AddTodoCommand o = (AddTodoCommand) obj;
            return this.todo.equals(o.todo);
        }
        return false;
    }
}
