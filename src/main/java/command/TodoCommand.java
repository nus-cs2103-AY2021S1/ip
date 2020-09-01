package command;

import java.io.IOException;

import exception.DescriptionException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import task.TodoTask;
import ui.Ui;

/**
 * TodoCommand would execute the program when user specify
 * "todo" in the command. It would create a new Task of type
 * TodoTask, add it to the list, and notify the user that the
 * new task was created, as well as updating the external file.
 */
public class TodoCommand extends Command {

    private String command;

    /**
     * Constructs a TodoCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public TodoCommand(String command) {
        super();
        this.command = command;
    }

    /**
     *Executes parsed user command. The result is:
     * 1. Creates a new Task of type TodoTask.
     * 2. Adds it to the tasks in TaskList.
     * 3. Notifies the user about the newly created task via Ui object.
     * 4. Updates the external file via Storage object.
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException Thrown when system failed to access external file.
     * @throws DescriptionException Thrown when user forget to include task
     * description in user command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException {
        String taskDescription = Parser.findTodoParser(this.command);
        TodoTask todoTask = new TodoTask(taskDescription);

        tasks.add(todoTask);

        storage.updateFile(tasks);

        return ui.getTaskMessage(todoTask, tasks.size());
    }
}
