package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to add a Todo task to the list.
 */
public class TodoCommand extends Command {
    /**
     * A string array that represents the instructions of this command.
     */
    private String[] nextCommandArr;
    
    public TodoCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Adds a Todo task to the taskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @throws DukeException if the instructions for this command is insufficient.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Todo newTodo = new Todo(nextCommandArr[1]);
            tasks.add(newTodo);
            ui.addTaskText(newTodo, tasks);
        } catch (Exception e) {
            throw new DukeException("The description of a todo cannot be empty~");
        }
        
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}
