package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
/**
 * Represents the Command to create a Todo
 */
public class CreateTodoCommand implements Command {
    private String taskTitle;

    /**
     * Return new Command that create an Event.
     * @param taskTitle title of the task to be created by this.
     * @throws DukeException when the format is corrupted.
     */
    public CreateTodoCommand(String taskTitle) throws DukeException {
        if (taskTitle.equals("")) {
            throw new DukeException("Task Title can't be empty");
        }
        this.taskTitle = taskTitle;
    }

    /**
     * Execute the command.
     *
     * @param storage             Storage to save data to.
     * @param tasks               The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The response of Duke to the user Input.
     * @throws DukeException if the system fails to execute.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        Task addedToDo = tasks.addTodo(taskTitle);
        return Ui.showTaskAdded(addedToDo.toString(), tasks.getTotalTask());
    }
}
