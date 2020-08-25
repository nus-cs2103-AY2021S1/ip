package duke.command;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import duke.ui.Ui;

/**
 * Is responsible for handing commands starting with <code>todo</code>.
 */
public class ToDoCommand extends TaskCreationCommand {
    public final static String COMMAND = "todo";
    
    private String description;

    /**
     * Creates a <code>ToDoCommand</code> object.
     * @param description The description of the task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new <code>ToDo</code> task and add pass it to <code>TaskCreationCommand</code>'s execute method
     * to add the task to the <code>TaskList</code>.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        super.execute(task, ui, tasks);
    }
}
