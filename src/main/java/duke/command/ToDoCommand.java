package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Implements to do command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new ToDo(description, false);
        tasks.addTask(newTask, ui);
        storage.saveTasks(tasks.getTasks());
        return ui.getResponses();
    }
}
