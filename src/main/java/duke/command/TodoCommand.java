package duke.command;


import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
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
     * @throws EmptyDescriptionException
     * @return todo added message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, String input) throws EmptyDescriptionException, DuplicateException {
        String description = Parser.parseTodo(input);
        Task task = new Todo(description);
        taskList.add(task);
        return ui.add(task);
    }
}
