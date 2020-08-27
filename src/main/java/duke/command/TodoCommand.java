package duke.command;


import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException {
        String description = Parser.parseTodo(input);
        Task task = new Todo(description);
        taskList.add(task);
        ui.add(task);
    }
}
