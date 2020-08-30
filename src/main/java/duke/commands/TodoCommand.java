package duke.commands;

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
    public boolean execute() {
        ui.askTodo();
        String todoName = sc.nextLine();
        tm.add(new Todo(todoName));
        return true;
    }
}