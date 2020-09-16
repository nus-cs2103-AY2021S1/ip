package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.TaskManager;
import duke.tasks.Todo;

/**
 * <code>TodoCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of adding todos to the task manager.
 */
public class TodoCommand extends Command {
    /**
     * Adds an <code>Todo</code> to the task manager.
     * @param input the user input.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) throws DukeException {
        tm.addTodo(new Todo(input));
        setDone();
        setResponse("Todo added");
        return true;
    }

    /**
     * Sets the initial response to ask for the name of the todo to be created.
     * In addition, sets the utility tools <code>tm</code> and <code>ui</code>.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askTodo());
        setUtility(tm, ui);
    }
}