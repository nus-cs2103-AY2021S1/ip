package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.task.ToDo;
import main.java.duke.Ui;

/**
 * Encapsulates a command to create a todo
 */
public class CreateTodoCommand extends Command {

    /** Description of the todo */
    private final String description;

    /** Initial completion status of the todo */
    private final boolean isComplete;

    /**
     * Constructor
     *
     * @param description Description of the todo
     * @param isComplete Initial completion status of the todo
     */
    public CreateTodoCommand(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Executes the command to create a todo
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ToDo todo = tasks.addTodo(this.description, this.isComplete);
        ui.printCreateTask(tasks, todo);
    }
}
