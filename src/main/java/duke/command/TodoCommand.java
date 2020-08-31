package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Represents a command to create a Todo Task. Created by using "todo description"
 */
public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a Todo with the user entered description, store it in TaskList, format a feedback String to be displayed
     * to user and store the new Todo in Storage.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @return Response object containing the feedback String to be displayed by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        String message = ui.formatMessage(String.format("Okay! I've added the following task: \n %s",
                todo.toString()));
        storage.updateTasks(tasks.getListOfTasks());
        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TodoCommand) {
            return this.description.equals(((TodoCommand) other).description);
        } else {
            return false;
        }
    }
}
