package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.task.ToDo;
import clippy.ui.Ui;

/**
 * Represents a command that adds a todo task when executed.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Constructs a command object that adds a todo task when executed.
     * 
     * @param taskDescription Literal description of the todo task.
     */
    public AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns resulting message to be displayed by GUI after adding a todo task.
     * Executes the command.
     * Save changes onto save file.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after adding a todo task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
