package main.command;

import main.task.TaskList;
import main.task.Todo;
import main.ui.Ui;

/**
 * Represents the add todo command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class TodoCommand implements Command {
    private final Todo todo;

    /**
     * Constructs an TodoCommand instance and the Todo object
     * with the description of the task.
     * @param description the description of the task.
     * @param tags the tags associated with the task.
     */
    public TodoCommand(String description, String[] tags) {
        todo = new Todo(description, tags);
    }

    /**
     * Adds the Todo object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string indicating the task has been added successfully.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) {
        tasks.add(todo);
        return ui.printAddSuccess(todo, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommandAfter() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand) {
            TodoCommand o = (TodoCommand) obj;
            return this.todo.equals(o.todo);
        }
        return false;
    }
}
