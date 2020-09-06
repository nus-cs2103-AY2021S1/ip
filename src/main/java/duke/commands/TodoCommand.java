package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;
import duke.tasks.Todo;

/**
 * <code>duke.commands.TodoCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of adding todos to the task manager.
 */
public class TodoCommand extends Command {
    /**
     * Adds an <code>Todo</code> to the task manager.
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the name of the todo to be created.
     * It uses the <code>Scanner</code> object in the parent class to receive the name the deadline.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>add</code> method with an <code>Todo</code> object passed as an argument.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        tm.add(new Todo(input));
        setDone();
        setResponse("Todo added"); // TODO: refactor this
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askTodo());
        setUtility(tm, ui);
    }
}