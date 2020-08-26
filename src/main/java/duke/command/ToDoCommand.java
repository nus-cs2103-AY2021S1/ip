package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.ToDo;

/**
 * Represents a user command to create a new <code>ToDo</code>.
 */
public class ToDoCommand extends ComplexCommand {

    /**
     * Creates a new <code>ToDoCommand</code> with the given parameters.
     *
     * @param params Parameters.
     */
    public ToDoCommand(String params) {
        super(params);
    }

    /**
     * Creates a new <code>ToDo</code> and stores it in <code>taskManager</code>.
     * Displays an error message without terminating software loop if parameters are invalid.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to create task
            ToDo newToDo = new ToDo(this.parseParams());

            // Store new task
            taskManager.storeTask(newToDo);

        } catch (DukeInputException e){
            ui.displayException(e);
        }

    }

    private String parseParams() throws DukeInputException {

        // Check if params is empty
        if (this.params.equals("")) {
            throw new DukeInputException("'todo' requires parameters.\n"
                    + "Use case: todo <name>");
        }

        return this.params;
    }

}
