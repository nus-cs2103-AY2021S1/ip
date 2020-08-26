package duke.command;

import duke.exception.*;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add a new todo task to the user's list of tasks.
 */
public class AddTodoCommand extends Command {

    protected final String[] parsedCommand;

    /**
     * Creates and initialises a new AddTodoCommand object
     *
     * @param parsedCommand String array that contains the todo task information.
     */
    public AddTodoCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Creates a new todo task, adds it to the list of tasks
     * and saves it into the designated file containing the user's list of tasks.
     *
     * @param tasks List of tasks which the new todo task will be added into.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If the task cannot be created due to invalid inputs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String todoInfo = this.retrieveTodoInfo();
            Task toAdd = new Todo(todoInfo.trim());
            tasks.addTask(toAdd);

            String successReply = "Success! This todo task has been added: \n\t" +
                    toAdd.toString() + "\nYou have " + tasks.getListSize() + " tasks in your list now.";
            ui.printReply(successReply);

            storage.saveFile(tasks);
        } catch (DukeException ex) {
            throw ex;
        }

    }

    /**
     * Retrieves the details of the todo task.
     *
     * @return String containing the todo description.
     * @throws InvalidTaskException If the deadline information is invalid and is missing arguments.
     */
    public String retrieveTodoInfo() throws DukeException {
        String todoInfo;
        if (this.parsedCommand.length == 0) {
            String err = "Your todo task description is empty. The task cannot be created.\n" +
                    "Type '/commands' to view the correct command for task creation! ";
            throw new InvalidTaskException(err);
        } else {
            todoInfo = this.parsedCommand[1];
        }
        return todoInfo;
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
