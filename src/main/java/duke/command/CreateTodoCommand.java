package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

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
     * @return Output strings displayed on the UI showing todo creation
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        ToDo newTodo = tasks.addTodo(description, isComplete);
        return ui.getCreateTaskStrings(tasks, newTodo);
    }
}
