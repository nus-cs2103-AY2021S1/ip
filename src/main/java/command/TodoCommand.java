package main.java.command;

import main.java.exception.DescriptionException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.task.TodoTask;
import main.java.ui.Ui;

import java.io.IOException;

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
        this.command =command;
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
     * @throws IOException
     * @throws DescriptionException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException {
        String taskDescription = Parser.findTodoParser(this.command);
        TodoTask todoTask = new TodoTask(taskDescription);

        tasks.add(todoTask);

        ui.getTaskMessage(todoTask,tasks.size());

        storage.updateFile(tasks);
    }
}
