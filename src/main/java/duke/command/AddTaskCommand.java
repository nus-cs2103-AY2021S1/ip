package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A command to add tasks. */
public abstract class AddTaskCommand extends Command {

    /** The description of the command. */
    protected String input;

    /**
     * Executes the command by adding a task into the taskList and saves the new taskList.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui       The UI of the bot.
     * @param storage  The storage system of the bot.
     * @throws DukeException If there is something wrong with the task input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        addTask(taskList, input);
        storage.saveTasks(taskList.getTasks());
        taskList.printNewTask();
    }

    /**
     * Adds a task into the @taskList.
     *
     * @param taskList The task list containing all saved tasks.
     * @param input    The description of the command.
     * @throws DukeException If the input is not in a valid format.
     */
    public abstract void addTask(TaskList taskList, String input) throws DukeException;
}
