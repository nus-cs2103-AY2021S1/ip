package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * Represents a command which creates a task with a todo.
 */
public class ToDoCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of an ToDoCommand.
     *
     * @param commandDetails String array with task details.
     */
    public ToDoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task with a todo to be added.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed ToDoCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(commandDetails[1]);
        tasks.getTasks().add(toDo);
        return ui.showTask(toDo, tasks.getTasks().size());
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
