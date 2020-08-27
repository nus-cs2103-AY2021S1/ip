package duke.command;


import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a todo command.
 */
public class TodoCommand implements Command {

    /**
     * Parses the input to extract the details: title.
     * Creates the Todo object.
     * Adds it to the user data.
     * Notifies user that it has been executed.
     *
     * @param taskList
     * @param ui
     * @param input
     * @throws InvalidTaskIdException
     * @throws EmptyDescriptionException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws EmptyDescriptionException {
        String description = Parser.parseTodo(input);
        Task task = new Todo(description);
        taskList.add(task);
        ui.add(task);
    }
}
